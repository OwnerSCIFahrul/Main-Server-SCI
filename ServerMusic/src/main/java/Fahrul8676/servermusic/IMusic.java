package Fahrul8676.servermusic;

public interface IMusic {

    String getMD5();

    long getDuration();

    String getName();

    default String getIdentifier() {
        return "radio." + this.getMD5();
    }
}