package lvy.so.picturescachedemo.ui;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import lvy.so.picturescachedemo.R;
import lvy.so.picturescachedemo.netutils.OkHttpUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerviewData;
    private Context mContext;
    private ArrayList<PictureBean.ResultsEntity> mList;
    private RecyclerViewDataAdapter mAdapter;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mContext = this;
        initView();
        initData();
    }

    private void initView() {
        gson = new Gson();
        mRecyclerviewData = (RecyclerView) findViewById(R.id.recyclerview_data);
        mRecyclerviewData.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mList = new ArrayList<>();
        mAdapter = new RecyclerViewDataAdapter(mContext, mList);
        mRecyclerviewData.setAdapter(mAdapter);
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        mRecyclerviewData.addItemDecoration(decoration);
    }

    /***
     * 加载网络数据
     */
    private void initData() {
        OkHttpUtils._asynchronousGet(OkHttpUtils.URL_PATH, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("-->##", "网络请求错误");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    if (result != null) {
                        Log.e("-->",result);
                        PictureBean pictureBean = gson.fromJson(result, PictureBean.class);
                        Log.e("***",pictureBean.getResults().size() +" ");
                        if (pictureBean.getResults().size() > 0) {
                            mList.clear();
                            mList.addAll(pictureBean.getResults());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mAdapter.notifyDataSetChanged();
                                }
                            });

                        }

                    }
                }
            }
        });
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space=space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left=space;
            outRect.right=space;
            outRect.bottom=space;
            if(parent.getChildAdapterPosition(view)==0){
                outRect.top=space;
            }
        }
    }
}
