package com.example.meetior.ui.Publish;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.meetior.Misc.Postinfo;
import com.example.meetior.Models.MyPostModel;
import com.example.meetior.Models.PublishPostInfo;
import com.example.meetior.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import static android.app.Activity.RESULT_OK;


public class PublishFragment extends Fragment {

    Button btnbrowse, btnpub;
    EditText epubtopic,epubtitle,epubdes,epubauth ;
    ImageView imgview;
    Uri FilePathUri;
    StorageReference storageReference;
    DatabaseReference databaseReference,db,dbb;

    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;

    private PublishViewModel publishViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_publish, container, false);
        epubtopic = root.findViewById(R.id.pubtopic);
        epubdes = root.findViewById(R.id.pubdes);
        epubtitle = root.findViewById(R.id.pubtitle);
        epubauth = root.findViewById(R.id.pubauth);

        storageReference = FirebaseStorage.getInstance().getReference("posts");
        databaseReference = FirebaseDatabase.getInstance().getReference("posts");
        btnbrowse = (Button)root.findViewById(R.id.pubbrowse);
        btnpub = root.findViewById(R.id.pubpub);

        progressDialog = new ProgressDialog(getActivity());// context name as per your project name


        btnbrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);

            }
        });
        btnpub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseUser curuser = FirebaseAuth.getInstance().getCurrentUser();
                String uid = curuser.getUid();
                db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("post");
                String time = getcurtime();
                Map<String, Object> val = new TreeMap<>();
                MyPostModel myPostModel = new MyPostModel(epubtopic.getText().toString(),epubtitle.getText().toString(),epubauth.getText().toString(),epubdes.getText().toString());
                val.put(time,myPostModel);
                db.updateChildren(val);
                dbb = FirebaseDatabase.getInstance().getReference().child("Browse");
                dbb.updateChildren(val);
            }
        });
        return root;
    }

    private String getcurtime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        String y = Integer.toString(year);
        String m = Integer.toString(month);
        String d = Integer.toString(date);
        if (m.length() != 2)
            y += "0";
        if (d.length() != 2)
            m += "0";
        String curdate = y + m + d;
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);
        String s = "";
        String h = Integer.toString(hour);
        String mm = Integer.toString(min);
        if (h.length() != 2)
            s += "0";
        s += h;
        if (mm.length() != 2)
            s += "0";
        s += mm;
        String curtime = s;
        Random rand = new Random();
        int rNum = 100 + rand.nextInt((999 - 100) + 1);
        String fin = curdate + curtime + Integer.toString(rNum);
        return fin;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), FilePathUri);
                imgview.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }


    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }


    /*public void UploadImage() {

        if (FilePathUri != null) {

            progressDialog.setTitle("Image is Uploading...");
            progressDialog.show();
            StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
            storageReference2.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String TempImageName = txtdata.getText().toString().trim();
                            progressDialog.dismiss();
                            Toast.makeText(getActivity().getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();
                            @SuppressWarnings("VisibleForTests")
                            PublishPostInfo imageUploadInfo = new  PublishPostInfo(TempImageName, taskSnapshot.getUploadSessionUri().toString());
                            String ImageUploadId = databaseReference.push().getKey();
                            databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                        }
                    });
        }
        else {

            Toast.makeText(getActivity(), "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }*/

}