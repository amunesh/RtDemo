package com.nx.demo;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.nx.demo.ApiUtil.APIUtils;
import com.nx.demo.ApiUtil.Cls_common;
import com.nx.demo.ApiUtil.DBhelper;
import com.nx.demo.ApiUtil.UserService;

import org.json.JSONArray;
import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostFragment extends Fragment {

    RecyclerView rec_post;
    TextView txtErrorn;
    ProgressBar progressBar;
    PostAdater postAdater;
    UserService userService;
    DBhelper myDb;
    SwipeRefreshLayout swiperefresh;

    public PostFragment() {
        // Required empty public constructor
    }

    JSONArray arraypost = new JSONArray();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vi = inflater.inflate(R.layout.fragment_post, container, false);
        userService = APIUtils.getUserService(getActivity());
        myDb = new DBhelper(getActivity());
        initView(vi);
        GetPOST();

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetPOST();
                swiperefresh.setRefreshing(false);
            }
        });
        return vi;
    }


    private void initView(View vi) {
        txtErrorn = vi.findViewById(R.id.txtErrorn);
        rec_post = vi.findViewById(R.id.rec_post);
        swiperefresh = vi.findViewById(R.id.swiperefresh);
        progressBar = vi.findViewById(R.id.progressBar);
        rec_post.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void GetPOST() {
        progressBar.setVisibility(View.VISIBLE);
        Call<Object> call = userService.postlist();
        call.enqueue(new Callback<Object>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                if (response.isSuccessful()) {
                    try {
                        progressBar.setVisibility(View.GONE);
                        //  JSONArray jsonArray=new JSONArray(response.body());
                        arraypost = new JSONArray(new Gson().toJson(response.body()));

                        postAdater = new PostAdater(getActivity(), arraypost);
                        rec_post.setItemViewCacheSize(arraypost.length());
                        rec_post.setAdapter(postAdater);

                        Cls_common.Log("jsonpost", "jsonpost" + arraypost.length());


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

    public void fragmentBecameVisible() {
        postAdater.notifyDataSetChanged();
    }

    private class PostAdater extends RecyclerView.Adapter<PostAdater.MyViewHolder> {
        Context context;
        JSONArray jsonArray = new JSONArray();

        public PostAdater(Context context, JSONArray jsonArray) {
            this.context = context;
            this.jsonArray = jsonArray;

        }

        @NonNull
        @Override
        public PostAdater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
            return new PostAdater.MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull PostAdater.MyViewHolder holder, int position) {
            try {


                holder.txt_body.setText(jsonArray.getJSONObject(position).getString("body"));
                holder.txt_title.setText(jsonArray.getJSONObject(position).getString("title"));
                Log.e("CheckData ", "CheckData " + myDb.CheckData(position));

                    if (myDb.CheckData(position+1)) {
                        holder.img_like.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_like));
                    } else {
                        holder.img_like.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_unlike));
                    }


                holder.img_like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        try {
                            holder.img_like.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_like));

                            boolean isInserted = myDb.inserRecordDebit(
                                    jsonArray.getJSONObject(position).getInt("id"),
                                    jsonArray.getJSONObject(position).getString("title"),
                                    jsonArray.getJSONObject(position).getString("body"), "true");

                            if (isInserted == true) {

                                Toast.makeText(context, "Like", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        @Override
        public int getItemCount() {
            return jsonArray.length();
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