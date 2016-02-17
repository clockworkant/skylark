package com.clockworkant.skylark.setdetail;

import com.clockworkant.skylark.api.model.Item;

/****
 * This class is a parcelable wrapper around item. It exists so that the domain items are not
 * required to be parcelable
 */
class ParcelableItem extends Item implements android.os.Parcelable {

    public ParcelableItem(Item item) {
        this.content_url = item.getContentUrl();
        this.content_type = item.getContentType();
        this.position = item.getPosition();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(this.content_url);
        dest.writeString(this.content_type);
        dest.writeInt(this.position);
    }

    public ParcelableItem() {
    }

    protected ParcelableItem(android.os.Parcel in) {
        this.content_url = in.readString();
        this.content_type = in.readString();
        this.position = in.readInt();
    }

    public static final Creator<ParcelableItem> CREATOR = new Creator<ParcelableItem>() {
        public ParcelableItem createFromParcel(android.os.Parcel source) {
            return new ParcelableItem(source);
        }

        public ParcelableItem[] newArray(int size) {
            return new ParcelableItem[size];
        }
    };

}
