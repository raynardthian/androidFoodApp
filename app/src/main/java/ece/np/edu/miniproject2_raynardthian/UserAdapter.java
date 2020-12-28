package ece.np.edu.miniproject2_raynardthian;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    ArrayList<String> users;

    public UserAdapter(ArrayList<String> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        holder.firstname.setText(users.get(position));
        holder.lastname.setText(users.get(position));
        holder.firstname.setText(users.get(position));

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView firstname;
        public TextView lastname;
        public TextView email;
         public ViewHolder(View itemView){
             super(itemView);
             firstname = itemView.findViewById(R.id.firstname);
             lastname = itemView.findViewById(R.id.lastname);
             email = itemView.findViewById(R.id.email);
         }
    }
}
