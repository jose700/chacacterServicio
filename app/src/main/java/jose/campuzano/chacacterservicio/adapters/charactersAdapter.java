package jose.campuzano.chacacterservicio.adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.fido.fido2.api.common.RequestOptions;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import jose.campuzano.chacacterservicio.MainActivity;
import jose.campuzano.chacacterservicio.R;
import jose.campuzano.chacacterservicio.models.characters;

public class charactersAdapter extends RecyclerView.Adapter<charactersAdapter.ViewHolder> {
    Context context;
    RequestOptions options;

    List<characters> mData;
    List<characters> mDataFiltered;

    public charactersAdapter(Context context, List<characters> mData) {
        this.context = context;
        this.mData = mData;
        this.mDataFiltered = mData;
    }
    public charactersAdapter(MainActivity.GetData getData, List<characters> moviesList) {
        this.mData = moviesList;
        this.mDataFiltered = moviesList;
    }
    public <moviesList> charactersAdapter(moviesList moviesList) {
    }

    @NonNull
    @Override
    public charactersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.item_movie_api, parent, false );
        return new charactersAdapter.ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText( mData.get( position ).getTitle() );
        //holder.item_movie_vote_average.setText( String.valueOf( mData.get( position ).getVote_average() ) );
        holder.img.setAnimation( AnimationUtils.loadAnimation( context, R.anim.fade_transition_animation ) );

        //glide
        Glide.with( context )
                .load( "https://image.tmdb.org/t/p/w500" + mData.get( position ).getImage() ).into( holder.img );
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, title, description, detail_movie_duration;
        // private TextView item_movie_vote_average;
        private ImageView img, detail_movie_img;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            tvTitle = itemView.findViewById( R.id.item_movie_title );
            //item_movie_vote_average = itemView.findViewById( R.id.item_movie_vote_average );
            img = itemView.findViewById( R.id.item_movie_img );
        }
}
}
