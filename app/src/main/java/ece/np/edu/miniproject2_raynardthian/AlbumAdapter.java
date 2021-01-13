package ece.np.edu.miniproject2_raynardthian;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ImageViewHolder> {
    ArrayList<String> FoodNames;
    ArrayList<String> foodCost;
    ArrayList<String> PicturePath;
    ArrayList<String> Description;
    private OnNoteListener mOnNoteListener;

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_layout, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view, mOnNoteListener);
        return imageViewHolder;
    }

    // This is to set the recyclerView Table Values
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
    // this is to set the details into the RecyclerView in the ShoppingActivity
//        holder.Album.setImageResource(image_id);
        holder.Album.setImageBitmap(BitmapFactory.decodeFile(PicturePath.get(position)));
        holder.AlbumTitle.setText(FoodNames.get(position));
        holder.AlbumCost.setText("$ " + foodCost.get(position));
        holder.AlbumDesc.setText(Description.get(position));
    }

    @Override
    public int getItemCount() {
        return FoodNames.size();

    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView Album;
        TextView AlbumTitle, AlbumCost,AlbumDesc;
        OnNoteListener onNoteListener;

        public ImageViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            Album = itemView.findViewById(R.id.album);
            AlbumTitle = itemView.findViewById(R.id.album_title);
            AlbumCost = itemView.findViewById(R.id.album_cost);
            AlbumDesc = itemView.findViewById(R.id.album_desc);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public AlbumAdapter(ArrayList<String> PicturePath, ArrayList<String> FoodNames, ArrayList<String> foodCost, ArrayList<String> Description, OnNoteListener onNoteListener) {
        this.PicturePath = PicturePath;
        this.FoodNames = FoodNames;
        this.foodCost = foodCost;
        this.Description = Description;
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