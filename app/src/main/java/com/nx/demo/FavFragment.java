package com.nx.demo;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nx.demo.ApiUtil.DBhelper;
import com.nx.demo.ApiUtil.DisplayData;
import com.nx.demo.ApiUtil.UserService;

import java.util.ArrayList;


public class FavFragment extends Fragment {

    DBhelper myDb;
    RecyclerView rec_postlike;
    TextView txtErrorn;
    ProgressBar progressBar;
    PostlikeAdater postlikeAdater;
    UserService userService;
    ArrayList<DisplayData> arrayList;
    SwipeRefreshLayout swiperefresh;

    public FavFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vi = inflater.inflate(R.layout.fragment_fav, container, false);
        myDb = new DBhelper(getActivity());
        initView(vi);
        ViewData();
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ViewData();
                swiperefresh.setRefreshing(false);
            }
        });
        return vi;
    }

    private void ViewData() {
        Cursor res = myDb.getAll();

        arrayList = new ArrayList<>();
        while (res.moveToNext()) {
            //Log.e("getString","getString"+res.getString(1));
            DisplayData displayData = new DisplayData(
                    res.getString(0),
                    res.getString(1),
                    res.getString(2),
                    res.getString(3));
            arrayList.add(displayData);
        }
        if (arrayList.size() > 0) {
            rec_postlike.setVisibility(View.VISIBLE);
            txtErrorn.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            postlikeAdater = new PostlikeAdater(getActivity(), arrayList);
            rec_postlike.setItemViewCacheSize(arrayList.size());
            rec_postlike.setAdapter(postlikeAdater);
        } else {
            txtErrorn.setVisibility(View.VISIBLE);
            rec_postlike.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        }

    }

    private void initView(View vi) {
        txtErrorn = vi.findViewById(R.id.txtErrorn);
        rec_postlike = vi.findViewById(R.id.rec_postlike);
        swiperefresh = vi.findViewById(R.id.swiperefresh);
        progressBar = vi.findViewById(R.id.progressBar);
        rec_postlike.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    public void fragmentBecameVisible() {
        ViewData();
    }

    private class PostlikeAdater extends RecyclerView.Adapter<PostlikeAdater.MyViewHolder> {
        Context context;
        ArrayList<DisplayData> likedata = new ArrayList<>();

        public PostlikeAdater(Context context, ArrayList<DisplayData> likedata) {
            this.context = context;
            this.likedata = likedata;

        }

        @NonNull
        @Override
        public PostlikeAdater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
            return new PostlikeAdater.MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull PostlikeAdater.MyViewHolder holder, int position) {
            try {

                Log.e("getId", "" + likedata.get(position).getId());
                holder.txt_body.setText(likedata.get(position).getMessage());
                holder.txt_title.setText(likedata.get(position).getTitle());
                if (likedata.get(position).getLike().equals("true")) {
                    holder.img_like.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_like));
                } else {
                    holder.img_like.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_unlike));
                }
                holder.img_like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        holder.img_like.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_unlike));


                        boolean isInserted = myDb.Delete_Record(Integer.parseInt(likedata.get(position).getId()));
                        if (isInserted) {
                            ViewData();
                        }


                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        @Override
        public int getItemCount() {
            return likedata.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView txt_body, txt_title;

            ImageView img_like;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                txt_title = itemView.findViewById(R.id.txt_title);
                txt_body = itemView.findViewById(R.id.txt_body);
                img_like = itemView.findViewById(R.id.img_like);

            }
        }
    }
}