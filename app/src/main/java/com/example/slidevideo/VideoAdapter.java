package com.example.slidevideo;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
/**
 * @author Devlin Hamill
 * CS460 Lab 2
 * Swipe application
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{
    /**
     * holds the list of videos and data associated with them
     */
    private List<VideoItem> videoItems;

    /**
     * takes in a list of video objects and assigns them to this instance of video objects
     * @param videoItems the object that has all relative video info
     */
    public VideoAdapter(List<VideoItem> videoItems) {
        this.videoItems = videoItems;
    }

    /**
     * inflates layout to fit the phone along with the any video data associated with it
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return The VideoViewHolder which contains the UI within the application
     */
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_video,parent,false)
        );
    }

    /**
     * Binds the video view holder to its position on the list
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.setVideoData(videoItems.get(position));

    }

    /**
     * checks how many videos exist
     * @return the amount of videos
     */
    @Override
    public int getItemCount() {

        return videoItems.size();

    }

    /**
     * holds all relative video information
     */
    static class VideoViewHolder extends RecyclerView.ViewHolder {
        /**
         * holds the video title information to connect the class to the UI and the video object
         */
        TextView textVideoTitle1;
        /**
         * holds the description of the video connecting the class to the UI and the video object
         */
        TextView textVideoDescription1;
        /**
         * Holds the ID number of the video connecting the class to the UI and the video object
         */
        TextView textVideoNum1;
        /**
         * holds the UI of the application
         */
        VideoView videoView;
        /**
         * Tells the user that the video is loading also connects the progress bar to the UI
         */
        ProgressBar progressBar;

        /**
         * A constructer that gets all video ui info before hand
         * @param itemView the UI connection that will be used to pull the id of each GUI component
         */
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            textVideoTitle1 = itemView.findViewById(R.id.textVideoTitle);
            textVideoDescription1 = itemView.findViewById(R.id.textVideoDescription);
            textVideoNum1 = itemView.findViewById(R.id.textVideonumber);
            progressBar = itemView.findViewById(R.id.videoProgressBar);
        }

        /**
         * intializes the media player by connecting each of the current video variables to the video object
         * @param videoItem the video object contains all relevant video data that needs to be displayed
         */
        void setVideoData(VideoItem videoItem){
            textVideoTitle1.setText(videoItem.videoTitle);
            textVideoDescription1.setText(videoItem.videoDescription);
            textVideoNum1.setText(videoItem.videoNum);
            videoView.setVideoPath(videoItem.videoURL);

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                /**
                 * sets up the video scale and the media player after the url is being perpared
                 * @param mp the MediaPlayer that is ready for playback
                 */
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    progressBar.setVisibility(View.GONE);
                    /**
                     * sets the video ration
                     */
                    float videoRatio = mp.getVideoWidth()/ (float) mp.getVideoHeight();
                    /**
                     * sets the screen ratio
                     */
                    float screenRatio = videoView.getWidth()/ (float) videoView.getHeight();
                    /**
                     * sets the video scale based on the video ratio and the screen ratio
                     */
                    float scale = videoRatio / screenRatio;
                    if(scale >= 1f){
                        videoView.setScaleX(scale);
                    }else{
                        videoView.setScaleY(1f / scale);
                    }
                }

            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                /**
                 * starts the media player after the video scale has been sucessfully set.
                 * @param mp the MediaPlayer that reached the end of the file
                 */
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });

        }

    }
}
