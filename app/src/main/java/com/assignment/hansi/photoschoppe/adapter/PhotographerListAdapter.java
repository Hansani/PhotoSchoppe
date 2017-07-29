package com.assignment.hansi.photoschoppe.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.assignment.hansi.photoschoppe.R;
import com.assignment.hansi.photoschoppe.model.Photographer;

import java.util.List;

/**
 * Created by hansanibiyanwila on 7/28/17.
 */

public class PhotographerListAdapter extends BaseAdapter {
    private Context context;
    private List<Photographer> photographerList;

    public PhotographerListAdapter(Context context, List<Photographer> photographerList) {
        this.context = context;
        this.photographerList = photographerList;
    }

    @Override
    public int getCount() {
        return photographerList.size();
    }

    @Override
    public Object getItem(int i) {
        return photographerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View cell_photographer;

        if (view == null) {
            cell_photographer = View.inflate(context, R.layout.cell_photographer, null);
        } else {
            cell_photographer = view;
        }

        TextView first_name = (TextView) cell_photographer.findViewById(R.id.txt_first_name_cell);
        TextView last_name = (TextView) cell_photographer.findViewById(R.id.txt_last_name_cell);
        TextView email = (TextView) cell_photographer.findViewById(R.id.txt_email_cell);
        TextView phone_no = (TextView) cell_photographer.findViewById(R.id.txt_phone_cell);

        first_name.setText(photographerList.get(i).getFirst_name());
        last_name.setText(photographerList.get(i).getLast_name());
        email.setText(photographerList.get(i).getEmail());
        phone_no.setText(photographerList.get(i).getPhone_no());

//        final String phone = phone_no.getText().toString().replaceAll("-", "");
//        final String email_ad = email.getText().toString();


//        phone_no.setOnClickListener(new View.OnClickListener() {
//            Context context;
//            @Override
//            public void onClick(View view) {
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                callIntent.setData(Uri.parse(phone));
//                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return;
//                }
//                startActivity(callIntent);
//            }
//        });

//        email.setOnClickListener(new View.OnClickListener() {
//            Context context;
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, MailActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("email_address",email_ad);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });


        return cell_photographer;
    }
}




