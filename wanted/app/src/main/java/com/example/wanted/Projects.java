package com.example.wanted;

public class Projects {
    private int id;
    private String title,detail,position,coverurl,imageurl,portraiturl,leaderid,user1id,user2id,user3id,num,tag;
    public Projects(String title,String detail,String position,String coverurl,String imageurl,String portraiturl,
                 String leaderid,String user1id,String user2id,String user3id,String num,String tag){
        super();
        this.title = title;
        this.detail = detail;
        this.position = position;
        this.coverurl = coverurl;
        this.imageurl = imageurl;
        this.portraiturl = portraiturl;
        this.leaderid = leaderid;
        this.user1id = user1id;
        this.user2id = user2id;
        this.user3id = user3id;
        this.num = num;
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getPortraiturl() {
        return portraiturl;
    }

    public void setPortraiturl(String portraiturl) {
        this.portraiturl = portraiturl;
    }

    public String getLeaderid() {
        return leaderid;
    }

    public void setLeaderid(String leaderid) {
        this.leaderid = leaderid;
    }

    public String getUser1id() {
        return user1id;
    }

    public void setUser1id(String user1id) {
        this.user1id = user1id;
    }

    public String getUser2id() {
        return user2id;
    }

    public void setUser2id(String user2id) {
        this.user2id = user2id;
    }

    public String getUser3id() {
        return user3id;
    }

    public void setUser3id(String user3id) {
        this.user3id = user3id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    @Override
    public String toString() {
        return "Projects{title="+title+"detail="+"detail="+detail+"position="+position+"coverurl="+coverurl+"imageurl="
                +imageurl+"portraiturl="+portraiturl+leaderid+user1id+user2id+user3id+num+tag+"}";
    }
}
