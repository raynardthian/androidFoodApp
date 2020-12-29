package ece.np.edu.miniproject2_raynardthian;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ImageViewHolder> {
    private int[] images;
    ArrayList<String> users;
    ArrayList<String> foodCost;
    private OnNoteListener mOnNoteListener;

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_layout, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view,mOnNoteListener);
        return imageViewHolder;
    }
    // This is to set the recyclerView Table Values
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        int image_id = images[position];
//        String foodPrice = String.valueOf(foodCost.get(position));
        holder.Album.setImageResource(image_id);
        holder.AlbumTitle.setText(users.get(position));
        holder.AlbumCost.setText("$ "+ foodCost.get(position));
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView Album;
        TextView AlbumTitle,AlbumCost;
        OnNoteListener onNoteListener;

        public ImageViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            Album = itemView.findViewById(R.id.album);
            AlbumTitle = itemView.findViewById(R.id.album_title);
            AlbumCost = itemView.findViewById(R.id.album_cost);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public AlbumAdapter(int[] images, ArrayList<String> users,ArrayList<String> foodCost, OnNoteListener onNoteListener) {
        this.images = images;
        this.users = users;
        this.foodCost = foodCost;
        this.mOnNoteListener = onNoteListener;
    }

    public interface OnNoteListener {
        void onNoteClick(int position);

    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.album_layout);
//    }
}