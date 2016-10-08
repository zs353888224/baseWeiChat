package com.wscq.baseWeiChat.domain.model;

import java.io.Serializable;

/**
 * Created by zs on 16/9/30.
 */
public class WechatEventMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    // 暂时不知道用途的属性
    private String Encrypt;
    private String Status;
    private String MenuId;

    // 因该是较完备的xml属性
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
    private String MsgId;
    private String Event;
    private String EventKey;
    private String Ticket;
    private String Latitude;
    private String Longitude;
    private String Precision;
    private String PicUrl;
    private String MediaId;
    private String Title;
    private String Description;
    private String URL;
    private String Location_X;
    private String Location_Y;
    private String Scale;
    private String Label;
    private String Content;
    private String Format;
    private String Recognition;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getLocation_X() {
        return Location_X;
    }

    public void setLocation_X(String location_X) {
        Location_X = location_X;
    }

    public String getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(String location_Y) {
        Location_Y = location_Y;
    }

    public String getScale() {
        return Scale;
    }

    public void setScale(String scale) {
        Scale = scale;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getRecognition() {
        return Recognition;
    }

    public void setRecognition(String recognition) {
        Recognition = recognition;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getPrecision() {
        return Precision;
    }

    public void setPrecision(String precision) {
        Precision = precision;
    }

    public String getEncrypt() {
        return Encrypt;
    }

    public void setEncrypt(String encrypt) {
        Encrypt = encrypt;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

    @Override
    public String toString() {
        return "WechatEventMessage{" +
                "Encrypt='" + Encrypt + '\'' +
                ", Status='" + Status + '\'' +
                ", MenuId='" + MenuId + '\'' +
                ", ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", MsgType='" + MsgType + '\'' +
                ", MsgId='" + MsgId + '\'' +
                ", Event='" + Event + '\'' +
                ", EventKey='" + EventKey + '\'' +
                ", Ticket='" + Ticket + '\'' +
                ", Latitude='" + Latitude + '\'' +
                ", Longitude='" + Longitude + '\'' +
                ", Precision='" + Precision + '\'' +
                ", PicUrl='" + PicUrl + '\'' +
                ", MediaId='" + MediaId + '\'' +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", URL='" + URL + '\'' +
                ", Location_X='" + Location_X + '\'' +
                ", Location_Y='" + Location_Y + '\'' +
                ", Scale='" + Scale + '\'' +
                ", Label='" + Label + '\'' +
                ", Content='" + Content + '\'' +
                ", Format='" + Format + '\'' +
                ", Recognition='" + Recognition + '\'' +
                '}';
    }
}
