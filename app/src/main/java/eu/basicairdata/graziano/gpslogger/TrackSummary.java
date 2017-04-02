/*
 * TrackSummary - Java Class for Android
 * Created by G.Capelli (BasicAirData) on 2/4/2017
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package eu.basicairdata.graziano.gpslogger;

import android.graphics.Bitmap;

public class TrackSummary {
    private long id;
    private String Name;
    private String Length;
    private String Duration;
    private String AltGap;
    private String MaxSpd;
    private String AvgSpd;
    private String Trackpoints;
    private String Placemarks;

    private int TrackType;
    private Bitmap Thumbnail;

    private int Progress;


    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLength() {
        return Length;
    }

    public void setLength(String length) {
        Length = length;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getAltGap() {
        return AltGap;
    }

    public void setAltGap(String altGap) {
        AltGap = altGap;
    }

    public String getMaxSpd() {
        return MaxSpd;
    }

    public void setMaxSpd(String maxSpd) {
        MaxSpd = maxSpd;
    }

    public String getAvgSpd() {
        return AvgSpd;
    }

    public void setAvgSpd(String avgSpd) {
        AvgSpd = avgSpd;
    }

    public String getTrackpoints() {
        return Trackpoints;
    }

    public void setTrackpoints(String trackpoints) {
        Trackpoints = trackpoints;
    }

    public String getPlacemarks() {
        return Placemarks;
    }

    public void setPlacemarks(String placemarks) {
        Placemarks = placemarks;
    }

    public int getTrackType() {
        return TrackType;
    }

    public void setTrackType(int trackType) {
        TrackType = trackType;
    }

    public Bitmap getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        Thumbnail = thumbnail;
    }

    public int getProgress() {
        return Progress;
    }

    public void setProgress(int progress) {
        Progress = progress;
    }

    public static TrackSummary getSummary(Track T) {
        PhysicalDataFormatter phdformatter = new PhysicalDataFormatter();
        PhysicalData phdDuration;
        PhysicalData phdSpeedMax;
        PhysicalData phdSpeedAvg;
        PhysicalData phdDistance;
        PhysicalData phdAltitudeGap;

        TrackSummary TS = new TrackSummary();

        TS.setName(T.getName());
        TS.setId(T.getId());
        phdDistance = phdformatter.format(T.getEstimatedDistance(),PhysicalDataFormatter.FORMAT_DISTANCE);
        TS.setLength(phdDistance.Value + " " + phdDistance.UM);
        phdDuration = phdformatter.format(T.getPrefTime(),PhysicalDataFormatter.FORMAT_DURATION);
        TS.setDuration(phdDuration.Value);
        phdAltitudeGap = phdformatter.format(T.getEstimatedAltitudeGap(GPSApplication.getInstance().getPrefEGM96AltitudeCorrection()),PhysicalDataFormatter.FORMAT_ALTITUDE);
        TS.setAltGap(phdAltitudeGap.Value + " " + phdAltitudeGap.UM);
        phdSpeedMax = phdformatter.format(T.getSpeedMax(),PhysicalDataFormatter.FORMAT_SPEED);
        TS.setMaxSpd(phdSpeedMax.Value + " " + phdSpeedMax.UM);
        phdSpeedAvg = phdformatter.format(T.getPrefSpeedAverage(),PhysicalDataFormatter.FORMAT_SPEED_AVG);
        TS.setAvgSpd(phdSpeedAvg.Value + " " + phdSpeedAvg.UM);

        TS.setTrackpoints(String.valueOf(T.getNumberOfLocations()));
        TS.setPlacemarks(String.valueOf(T.getNumberOfPlacemarks()));

        TS.setProgress(0);



        return TS;
    }
}
