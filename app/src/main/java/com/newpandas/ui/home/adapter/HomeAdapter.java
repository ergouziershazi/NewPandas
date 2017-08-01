package com.newpandas.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newpandas.R;
import com.newpandas.model.entity.CcTvForBean;
import com.newpandas.model.entity.LightChinaBean;
import com.newpandas.model.entity.PandaEyeBean;
import com.newpandas.model.entity.PandaHome;
import com.newpandas.net.HttpFactory;

import java.util.List;

/**
 * Created by yan on 2017/7/28.
 */

public class HomeAdapter extends RecyclerView.Adapter{

    private List<Object> datas;
    public static final int ITEMCOUNT = 8;//加载9种不同类型的item
    public static final int AREA = 0;//代表精彩推荐
    public static final int PANDAEYE = 1;//代表熊猫观察
    public static final int PANDALIVE = 2;//熊猫直播
    public static final int WALLLIVE = 3;//代表长城直播
    public static final int CHINALIVE = 4;//代表直播中国
    public static final int INTERACTIVE = 5;//代表精彩策划
    public static final int CCTV = 6;//代表样式名栏
    public static final int LIST = 7;//代表光影中国
    private Context context;
    private LayoutInflater inflater;
    private List<CcTvForBean.ListBean> cctvlist;
    private List<LightChinaBean.ListBean> lightlist;
    private List<PandaEyeBean.ListBean> pandaeyelist;
    public HomeAdapter(Context context, List<Object> datas, List<CcTvForBean.ListBean> cctvlist, List<LightChinaBean.ListBean> lightlist, List<PandaEyeBean.ListBean> pandaeyelist){
        this.context=context;
        this.datas=datas;
        this.inflater = LayoutInflater.from(context);
        this.cctvlist=cctvlist;
        this.lightlist=lightlist;
        this.pandaeyelist=pandaeyelist;
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = datas.get(position);
         if(obj instanceof PandaHome.DataBean.AreaBean) {
          return AREA;
        }else if(obj instanceof PandaHome.DataBean.PandaeyeBean) {
            return PANDAEYE;
        }else if(obj instanceof PandaHome.DataBean.PandaliveBean) {
            return PANDALIVE;
        }else if(obj instanceof PandaHome.DataBean.WallliveBean) {
            return WALLLIVE;
        }else if(obj instanceof PandaHome.DataBean.ChinaliveBean) {
            return CHINALIVE;
        }else if(obj instanceof PandaHome.DataBean.InteractiveBean) {
            return INTERACTIVE;
        }else if(obj instanceof PandaHome.DataBean.CctvBean) {
            return CCTV;
        }else if(obj instanceof PandaHome.DataBean.ListBeanXXX) {
            return LIST;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case AREA:
                View areaview=inflater.inflate(R.layout.home_area,null);
                return new AreaViewHolder(areaview);
            case PANDAEYE:
//                熊猫观察
                View pandaeyeview=inflater.inflate(R.layout.home_panda_broadcast,null);
                return  new PandaEyeHolder (pandaeyeview);

            case PANDALIVE:
//                熊猫直播
                View pandalive=inflater.inflate(R.layout.home_panda_live,null);
                return  new PandaliveHolder(pandalive);

            case WALLLIVE:
//          长城直播
                View wallliveview=inflater.inflate(R.layout.home_walllive_view,null);
                return  new WallLiveViewHolder(wallliveview);

            case CHINALIVE:
                View chinaliveview=inflater.inflate(R.layout.home_live_china,null);
                return  new ChinaLiveHolder(chinaliveview);

            case INTERACTIVE:
//                精彩策划
                View interactive=inflater.inflate(R.layout.home_special_planning,null);
                return  new InteRactiveHoldr(interactive);

//            case CCTV:
//                View cctvview=inflater.inflate(R.layout.home_cctv,null);
//                return  new CctvViewHolder(cctvview);
//            case LIST:
//                View listview=inflater.inflate(R.layout.home_light_china,null);
//                return  new InteRactiveHoldr(listview);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case AREA:
                AreaViewHolder areaholder= (AreaViewHolder) holder;
                PandaHome.DataBean.AreaBean arealist= (PandaHome.DataBean.AreaBean) datas.get(position);
                loadArea(areaholder,arealist);
                break;
            case PANDAEYE:
                PandaEyeHolder pandaEyeHolder= (PandaEyeHolder) holder;
                PandaHome.DataBean.PandaeyeBean pandaeyelist= (PandaHome.DataBean.PandaeyeBean) datas.get(position);
                loadPandaEye(pandaEyeHolder,pandaeyelist);
                break;
            case PANDALIVE:
                PandaliveHolder pandaliveHolder= (PandaliveHolder) holder;
                PandaHome.DataBean.PandaliveBean pandalivelist= (PandaHome.DataBean.PandaliveBean) datas.get(position);
                loadPandaLive(pandaliveHolder,pandalivelist);
                break;
            case WALLLIVE:
                WallLiveViewHolder wallLiveViewHolder= (WallLiveViewHolder) holder;
                PandaHome.DataBean.WallliveBean wallliveBean= (PandaHome.DataBean.WallliveBean) datas.get(position);
                loadWallLive(wallLiveViewHolder,wallliveBean);
                break;
            case CHINALIVE:
                ChinaLiveHolder chinaLiveHolder= (ChinaLiveHolder) holder;
                PandaHome.DataBean.ChinaliveBean chinaliveBean= (PandaHome.DataBean.ChinaliveBean) datas.get(position);
                loadChinaLive(chinaLiveHolder,chinaliveBean);
                break;
            case INTERACTIVE:
                InteRactiveHoldr inteRactiveHoldr= (InteRactiveHoldr) holder;
                PandaHome.DataBean.InteractiveBean interactiveBean= (PandaHome.DataBean.InteractiveBean) datas.get(position);
                loadInterActive(inteRactiveHoldr,interactiveBean);
                break;
//            case CCTV:
//                CctvViewHolder cctvViewHolder= (CctvViewHolder) holder;
//                CcTvForBean.ListBean listBean= (CcTvForBean.ListBean) datas.get(position);
//                loadCCTV(cctvViewHolder,listBean);
//                break;
//            case LIST:
//                lightchinaholder lightchinaholder= (HomeAdapter.lightchinaholder) holder;
//                LightChinaBean listBean1= (LightChinaBean) datas.get(position);
//                loadLightChina(lightchinaholder,listBean1);
//                break;
        }
    }


    private void loadArea(AreaViewHolder holder,PandaHome.DataBean.AreaBean areaBean){
        List<PandaHome.DataBean.AreaBean.ListscrollBean> areas = areaBean.getListscroll();
        ImageView areaIcon = holder.areaIcon;
        HttpFactory.create().loadImage(areaBean.getImage(),areaIcon);
        RecyclerView recyclerView = holder.recyclerView;
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new HomeAreaAdapter(context,R.layout.home_area_item,areas));
    }
  private void loadPandaEye(PandaEyeHolder holder,PandaHome.DataBean.PandaeyeBean pandaeyeBean){
        holder.title.setText(pandaeyeBean.getTitle());
        ImageView imagelogo=holder.imagelogo;
       HttpFactory.create().loadImage(pandaeyeBean.getPandaeyelogo(),imagelogo);
       List<PandaHome.DataBean.PandaeyeBean.ItemsBean> items = pandaeyeBean.getItems();
       LinearLayoutManager manager=new LinearLayoutManager(context);
      manager.setOrientation(LinearLayoutManager.VERTICAL);
      RecyclerView recyclerView=holder.recyclerView;
      recyclerView.setLayoutManager(manager);
      recyclerView.setAdapter(new PandaeyeTopAdapter(context,R.layout.home_pandaeye_top_item,items));

  }
    private void loadPandaLive(PandaliveHolder holder,PandaHome.DataBean.PandaliveBean pandaliveBean){
        List<PandaHome.DataBean.PandaliveBean.ListBean> pandalist = pandaliveBean.getList();
        holder.pandalivetext.setText(pandaliveBean.getTitle());
        RecyclerView recyclerView=holder.pandarecy;
        GridLayoutManager manager=new GridLayoutManager(context,3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new PandaLiveAdapter(context,R.layout.home_pandalive_item,pandalist));
    }
   private void loadWallLive(WallLiveViewHolder holder,PandaHome.DataBean.WallliveBean walllivebean){
       List<PandaHome.DataBean.WallliveBean.ListBeanX> walllist = walllivebean.getList();
       holder.walltext.setText(walllivebean.getTitle());
       RecyclerView recyclerView=holder.wallrecylerview;
       GridLayoutManager manager=new GridLayoutManager(context,3);
       recyclerView.setLayoutManager(manager);
       manager.setOrientation(GridLayoutManager.VERTICAL);
       recyclerView.setAdapter(new WallLiveAdapter(context,R.layout.home_pandalive_item,walllist));

   }
    private void loadChinaLive(ChinaLiveHolder holder,PandaHome.DataBean.ChinaliveBean chinaliveBean){
        List<PandaHome.DataBean.ChinaliveBean.ListBeanXX> list = chinaliveBean.getList();
        holder.chinatext.setText(chinaliveBean.getTitle());
        RecyclerView recyclerView=holder.chinarecyler;
        GridLayoutManager manager=new GridLayoutManager(context,3);
        recyclerView.setLayoutManager(manager);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setAdapter(new ChinaLiveAdpter(context,R.layout.home_pandalive_item,list));

    }
    private void loadInterActive(InteRactiveHoldr holdr,PandaHome.DataBean.InteractiveBean interactiveBean){
        holdr.intetext.setText(interactiveBean.getTitle());
        ImageView imageView=holdr.inteimage;
        HttpFactory.create().loadImage(interactiveBean.getInteractiveone().get(0).getImage(),imageView);
    }
    private void loadCCTV(CctvViewHolder holder, CcTvForBean.ListBean ccTvForBean){
        List<CcTvForBean.ListBean> list = (List<CcTvForBean.ListBean>) ccTvForBean;
        holder.title.setText(ccTvForBean.getTitle());
        RecyclerView recyclerView=holder.recyclerView;
        GridLayoutManager manager=new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(manager);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setAdapter(new CcTVAdapter(context,R.layout.home_cctv_item,cctvlist));


    }
    private void loadLightChina(lightchinaholder holder, LightChinaBean lightChinaBean){
//        List<LightChinaBean.ListBean> list = lightChinaBean.getList();
        holder.title.setText(lightChinaBean.getList().get(0).getTitle());
        RecyclerView recyclerView=holder.recyclerView;
        LinearLayoutManager manager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setAdapter(new LightChinaAdapter(context,R.layout.home_lightchina_item,lightlist));

    }
    @Override
    public int getItemCount() {
        return 6;
    }

//    精彩推荐
    class AreaViewHolder extends RecyclerView.ViewHolder{
         ImageView areaIcon;
        RecyclerView recyclerView;
        public AreaViewHolder(View itemView) {
            super(itemView);
            recyclerView= (RecyclerView) itemView.findViewById(R.id.areaRecyclerView);
            areaIcon = (ImageView) itemView.findViewById(R.id.areaIcon);
        }
    }
//    熊猫观察
    class PandaEyeHolder extends RecyclerView.ViewHolder{
         TextView title;
        ImageView imagelogo;
        RecyclerView recyclerView;

    public PandaEyeHolder(View itemView) {
        super(itemView);
        title= (TextView) itemView.findViewById(R.id.panda_watch);
        imagelogo= (ImageView) itemView.findViewById(R.id.panda_watch_img);
        recyclerView= (RecyclerView) itemView.findViewById(R.id.panda_watch_title_recyclerview);
    }
}
//    熊猫直播
    class PandaliveHolder extends RecyclerView.ViewHolder{
       TextView pandalivetext;
       RecyclerView pandarecy;

    public PandaliveHolder(View itemView) {
        super(itemView);
        pandalivetext= (TextView) itemView.findViewById(R.id.live_panda_titles);
        pandarecy= (RecyclerView) itemView.findViewById(R.id.panda_live_recyclerview);
    }
}



//    长城直播
   class WallLiveViewHolder extends RecyclerView.ViewHolder{
     TextView walltext;
       RecyclerView wallrecylerview;
    public WallLiveViewHolder(View itemView) {
        super(itemView);
        walltext= (TextView) itemView.findViewById(R.id.live_title);
        wallrecylerview= (RecyclerView) itemView.findViewById(R.id.live_recyclerview);
    }
}

//    直播中国
    class ChinaLiveHolder extends RecyclerView.ViewHolder{
       TextView chinatext;
        RecyclerView chinarecyler;

    public ChinaLiveHolder(View itemView) {
        super(itemView);
        chinatext= (TextView) itemView.findViewById(R.id.china_live_title);
        chinarecyler= (RecyclerView) itemView.findViewById(R.id.china_live_recyclerview);
    }
}
 //代表特别策划
    class InteRactiveHoldr extends RecyclerView.ViewHolder{
      TextView intetext;
      ImageView inteimage;

     public InteRactiveHoldr(View itemView) {
         super(itemView);
         intetext= (TextView) itemView.findViewById(R.id.special_planning);
         inteimage= (ImageView) itemView.findViewById(R.id.special_planning_img);
     }
 }
//    央视名栏
    class CctvViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        RecyclerView recyclerView;
        public CctvViewHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.cctv_live_title);
            recyclerView= (RecyclerView) itemView.findViewById(R.id.cctv_live_recyclerview);
        }
    }
//    光影中国
    class  lightchinaholder extends RecyclerView.ViewHolder{
        TextView title;
        RecyclerView recyclerView;
        public lightchinaholder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.light_live_title);
            recyclerView= (RecyclerView) itemView.findViewById(R.id.light_live_recyclerview);
        }
    }

}
