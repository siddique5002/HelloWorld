package Data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.helloworld.helloworldtest.AppController;
import com.helloworld.helloworldtest.LocationDetailView;
import com.helloworld.helloworldtest.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import Model.Office;

/**
 * Created by Siddique on 12/1/2015.
 */
public class CustomListviewAdapter extends ArrayAdapter<Office> {

    private LayoutInflater inflater;
    private ArrayList<Office> data;
    private Activity mContext;
    private int LayoutResourceId;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListviewAdapter(Activity context, int resource, ArrayList<Office> objs) {
        super(context, resource, objs);
        data = objs;
        mContext = context;
        LayoutResourceId = resource;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Office getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(Office item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        ViewHolder viewHolder = null;

        if (row == null) {
            inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(LayoutResourceId, parent, false);
            viewHolder = new ViewHolder();

            // get ref

            //viewHolder.bandImage = (NetworkImageView)row.findViewById(R.id.imageView);
            viewHolder.name = (TextView) row.findViewById(R.id.officeNameL);
            viewHolder.address = (TextView) row.findViewById(R.id.officeAddressL);
            viewHolder.DistanceL = (TextView) row.findViewById(R.id.DistanceL);
            //viewHolder.where = (TextView) row.findViewById(R.id.whereText);

            row.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) row.getTag();
        }

        viewHolder.office = data.get(position);

        viewHolder.name.setText("Name:  " + viewHolder.office.getOfficename());
        viewHolder.address.setText("Address: " + viewHolder.office.getAddress());
        viewHolder.DistanceL.setText("Distance: " + new DecimalFormat("##.##").format(viewHolder.office.getDistance()) + "mile");

        final ViewHolder finalViewHolder = viewHolder;
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, LocationDetailView.class);
                Bundle mbundle = new Bundle();
                mbundle.putSerializable("officeObj", finalViewHolder.office);
                i.putExtras(mbundle);
                mContext.startActivity(i);

            }
        });


        return row;
    }

    public class ViewHolder {
        Office office;
        TextView name;
        TextView address;
        TextView where;
        TextView when;
        TextView DistanceL;
        String website;
        NetworkImageView bandImage;
    }


}
