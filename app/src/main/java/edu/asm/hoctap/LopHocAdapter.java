package edu.asm.hoctap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;



import java.util.ArrayList;

public class LopHocAdapter extends BaseAdapter {
    Context c;
    ArrayList<LopHoc> dslh = new ArrayList<>();

    public LopHocAdapter(Context c, ArrayList<LopHoc> dslh) {
        this.c = c;
        this.dslh = dslh;
    }

    public LopHocAdapter(LopHocActivity c, ArrayList<LopHoc> dslh) {
    }

    @Override
    public int getCount() {
        return dslh.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inf = ((Activity)c).getLayoutInflater();
        view = inf.inflate(R.layout.layout_lophoc_listview,null);
        TextView tvID = view.findViewById(R.id.tvIdLop);
        TextView tvLop = view.findViewById(R.id.tvTenLop);
        Button btEdit = view.findViewById(R.id.btnEdit);
        Button btDelete = view.findViewById(R.id.btnDelete);

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LopHoc lh = dslh.get(i);
                ((LopHocActivity)c).ValuesClassEdit(lh);
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;int _id = dslh.get(i).get_id();
                ((LopHocActivity)c).deletelop(_id);
            }
        });

        LopHoc lh = dslh.get(i);
        tvID.setText(lh.get_id()+"");
        tvLop.setText(lh.getTenLop());
        return view;
    }
}
