package app.volley.adapters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URLDecoder;
import java.util.ArrayList;

import app.volley.R;
import app.volley.response.Hero;

public class DataAadpter extends RecyclerView.Adapter<DataAadpter.viewHolder> {

    private Context mcontext;
    private ArrayList<Hero> heroArrayList;

    public DataAadpter(Context mcontext, ArrayList<Hero> heroArrayList) {
        this.mcontext = mcontext;
        this.heroArrayList = heroArrayList;
    }

    public void setData(Context context, ArrayList<Hero> list) {
        mcontext = context;
        heroArrayList = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.single_item_row,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        try{

            Hero hero = heroArrayList.get(position);
            holder.name.setText(hero.getName());
            String decoded_url = URLDecoder.decode(hero.getImageUrl(), "UTF-8");

            if (decoded_url != null && !TextUtils.isEmpty(decoded_url))
                Picasso.with(mcontext).load(decoded_url).into(holder.image);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (heroArrayList != null) {
            return heroArrayList.size();
        }
        return 0;
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView image;

        public viewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.text_name);
            image = itemView.findViewById(R.id.image_url);
        }
    }


}
