package com.newpandas.model.entity.pandalive;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yan on 2017/7/31.
 */

public class OtherDetilsBean {
    /**
     * videoset : {"0":{"vsid":"VSET100167216881","name":"熊猫频道-精彩一刻","img":"http://p5.img.cctvpic.com/fmspic/vms/image/2013/06/21/VSET_1371809214479325.jpg","enname":"其他","url":"http://tv.cntv.cn/videoset/VSET100167216881","cd":"","zy":"","bj":"","dy":"","js":"","nf":"","yz":"","fl":"","sbsj":"2013-05-01","sbpd":"其他","desc":"精彩一刻栏目关注人气较高的熊猫个体，精选每日直播中最吸引人的画面，呈现熊猫生活中最精彩的状态。","playdesc":"","zcr":"","fcl":""},"count":"4412"}
     * video : [{"vsid":"VSET100167216881","order":"4413","vid":"6b0bf51b670a4cf2988234ff041ad241","t":"《精彩一刻》 20170713 奶爸别光拍，再去拿点花花来","url":"http://tv.cntv.cn/video/VSET100167216881/6b0bf51b670a4cf2988234ff041ad241","ptime":"2017-07-13 15:38:23","img":"http://p4.img.cctvpic.com/fmspic/2017/07/13/6b0bf51b670a4cf2988234ff041ad241-33.jpg?p=2&h=120","len":"00:00:46","em":"CM01"},{"vsid":"VSET100167216881","order":"4416","vid":"a8624d5c9218485c99895f9d21afa8b7","t":"《精彩一刻》 20170713 让我看看，还是这儿吃舒服","url":"http://tv.cntv.cn/video/VSET100167216881/a8624d5c9218485c99895f9d21afa8b7","ptime":"2017-07-13 15:37:23","img":"http://p5.img.cctvpic.com/fmspic/2017/07/13/a8624d5c9218485c99895f9d21afa8b7-9.jpg?p=2&h=120","len":"00:00:17","em":"CM01"},{"vsid":"VSET100167216881","order":"4415","vid":"4834d3af76684e8aa3cef302368d894d","t":"《精彩一刻》 20170713 窝窝头，我会保护你的！","url":"http://tv.cntv.cn/video/VSET100167216881/4834d3af76684e8aa3cef302368d894d","ptime":"2017-07-13 15:36:28","img":"http://p1.img.cctvpic.com/fmspic/2017/07/13/4834d3af76684e8aa3cef302368d894d-33.jpg?p=2&h=120","len":"00:00:46","em":"CM01"},{"vsid":"VSET100167216881","order":"4414","vid":"6d3ebd648b814cec92665199cc78c7e7","t":"《精彩一刻》 20170713 这睡姿！爱我你怕了吗！","url":"http://tv.cntv.cn/video/VSET100167216881/6d3ebd648b814cec92665199cc78c7e7","ptime":"2017-07-13 15:35:28","img":"http://p1.img.cctvpic.com/fmspic/2017/07/13/6d3ebd648b814cec92665199cc78c7e7-9.jpg?p=2&h=120","len":"00:00:18","em":"CM01"},{"vsid":"VSET100167216881","order":"4411","vid":"94970834b0d1480ca3950471469778d6","t":"《精彩一刻》 20170713 熊猫版的\u201c暗中观察\u201d","url":"http://tv.cntv.cn/video/VSET100167216881/94970834b0d1480ca3950471469778d6","ptime":"2017-07-13 12:57:58","img":"http://p3.img.cctvpic.com/fmspic/2017/07/13/94970834b0d1480ca3950471469778d6-32.jpg?p=2&h=120","len":"00:00:44","em":"CM01"},{"vsid":"VSET100167216881","order":"4410","vid":"3bececdb455547f1bd2f7f4c7610d748","t":"《精彩一刻》 20170713 看老娘如何防守上树","url":"http://tv.cntv.cn/video/VSET100167216881/3bececdb455547f1bd2f7f4c7610d748","ptime":"2017-07-13 12:56:52","img":"http://p4.img.cctvpic.com/fmspic/2017/07/13/3bececdb455547f1bd2f7f4c7610d748-21.jpg?p=2&h=120","len":"00:00:33","em":"CM01"},{"vsid":"VSET100167216881","order":"4412","vid":"3057ebac2e4c4284806564fbc8988e28","t":"《精彩一刻》 20170713 奶爸坏坏！人家胆子小啦","url":"http://tv.cntv.cn/video/VSET100167216881/3057ebac2e4c4284806564fbc8988e28","ptime":"2017-07-13 12:55:51","img":"http://p2.img.cctvpic.com/fmspic/2017/07/13/3057ebac2e4c4284806564fbc8988e28-20.jpg?p=2&h=120","len":"00:00:31","em":"CM01"}]
     */

    private VideosetBean videoset;
    private List<VideoBean> video;

    public VideosetBean getVideoset() {
        return videoset;
    }

    public void setVideoset(VideosetBean videoset) {
        this.videoset = videoset;
    }

    public List<VideoBean> getVideo() {
        return video;
    }

    public void setVideo(List<VideoBean> video) {
        this.video = video;
    }

    public static class VideosetBean {
        /**
         * 0 : {"vsid":"VSET100167216881","name":"熊猫频道-精彩一刻","img":"http://p5.img.cctvpic.com/fmspic/vms/image/2013/06/21/VSET_1371809214479325.jpg","enname":"其他","url":"http://tv.cntv.cn/videoset/VSET100167216881","cd":"","zy":"","bj":"","dy":"","js":"","nf":"","yz":"","fl":"","sbsj":"2013-05-01","sbpd":"其他","desc":"精彩一刻栏目关注人气较高的熊猫个体，精选每日直播中最吸引人的画面，呈现熊猫生活中最精彩的状态。","playdesc":"","zcr":"","fcl":""}
         * count : 4412
         */

        @SerializedName("0")
        private _$0Bean _$0;
        private String count;

        public _$0Bean get_$0() {
            return _$0;
        }

        public void set_$0(_$0Bean _$0) {
            this._$0 = _$0;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public static class _$0Bean {
            /**
             * vsid : VSET100167216881
             * name : 熊猫频道-精彩一刻
             * img : http://p5.img.cctvpic.com/fmspic/vms/image/2013/06/21/VSET_1371809214479325.jpg
             * enname : 其他
             * url : http://tv.cntv.cn/videoset/VSET100167216881
             * cd :
             * zy :
             * bj :
             * dy :
             * js :
             * nf :
             * yz :
             * fl :
             * sbsj : 2013-05-01
             * sbpd : 其他
             * desc : 精彩一刻栏目关注人气较高的熊猫个体，精选每日直播中最吸引人的画面，呈现熊猫生活中最精彩的状态。
             * playdesc :
             * zcr :
             * fcl :
             */

            private String vsid;
            private String name;
            private String img;
            private String enname;
            private String url;
            private String cd;
            private String zy;
            private String bj;
            private String dy;
            private String js;
            private String nf;
            private String yz;
            private String fl;
            private String sbsj;
            private String sbpd;
            private String desc;
            private String playdesc;
            private String zcr;
            private String fcl;

            public String getVsid() {
                return vsid;
            }

            public void setVsid(String vsid) {
                this.vsid = vsid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getEnname() {
                return enname;
            }

            public void setEnname(String enname) {
                this.enname = enname;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getCd() {
                return cd;
            }

            public void setCd(String cd) {
                this.cd = cd;
            }

            public String getZy() {
                return zy;
            }

            public void setZy(String zy) {
                this.zy = zy;
            }

            public String getBj() {
                return bj;
            }

            public void setBj(String bj) {
                this.bj = bj;
            }

            public String getDy() {
                return dy;
            }

            public void setDy(String dy) {
                this.dy = dy;
            }

            public String getJs() {
                return js;
            }

            public void setJs(String js) {
                this.js = js;
            }

            public String getNf() {
                return nf;
            }

            public void setNf(String nf) {
                this.nf = nf;
            }

            public String getYz() {
                return yz;
            }

            public void setYz(String yz) {
                this.yz = yz;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getSbsj() {
                return sbsj;
            }

            public void setSbsj(String sbsj) {
                this.sbsj = sbsj;
            }

            public String getSbpd() {
                return sbpd;
            }

            public void setSbpd(String sbpd) {
                this.sbpd = sbpd;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPlaydesc() {
                return playdesc;
            }

            public void setPlaydesc(String playdesc) {
                this.playdesc = playdesc;
            }

            public String getZcr() {
                return zcr;
            }

            public void setZcr(String zcr) {
                this.zcr = zcr;
            }

            public String getFcl() {
                return fcl;
            }

            public void setFcl(String fcl) {
                this.fcl = fcl;
            }
        }
    }

    public static class VideoBean {
        /**
         * vsid : VSET100167216881
         * order : 4413
         * vid : 6b0bf51b670a4cf2988234ff041ad241
         * t : 《精彩一刻》 20170713 奶爸别光拍，再去拿点花花来
         * url : http://tv.cntv.cn/video/VSET100167216881/6b0bf51b670a4cf2988234ff041ad241
         * ptime : 2017-07-13 15:38:23
         * img : http://p4.img.cctvpic.com/fmspic/2017/07/13/6b0bf51b670a4cf2988234ff041ad241-33.jpg?p=2&h=120
         * len : 00:00:46
         * em : CM01
         */

        private String vsid;
        private String order;
        private String vid;
        private String t;
        private String url;
        private String ptime;
        private String img;
        private String len;
        private String em;

        public String getVsid() {
            return vsid;
        }

        public void setVsid(String vsid) {
            this.vsid = vsid;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }

        public String getT() {
            return t;
        }

        public void setT(String t) {
            this.t = t;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getLen() {
            return len;
        }

        public void setLen(String len) {
            this.len = len;
        }

        public String getEm() {
            return em;
        }

        public void setEm(String em) {
            this.em = em;
        }
    }
}
