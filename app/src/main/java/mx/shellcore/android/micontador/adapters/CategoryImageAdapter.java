package mx.shellcore.android.micontador.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mx.shellcore.android.micontador.R;
import mx.shellcore.android.micontador.model.CategoryImage;
import mx.shellcore.android.micontador.utils.Base64Images;

public class CategoryImageAdapter extends RecyclerView.Adapter<CategoryImageAdapter.ViewHolder> {

    private ArrayList<CategoryImage> images;
    private Context context;

    OnItemClickListener onItemClickListener;

    public CategoryImageAdapter(Context context, ArrayList<CategoryImage> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_category_image, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CategoryImage categoryImage = images.get(position);

        if (categoryImage.getImage() != null) {
            holder.setImgCategoryImage(categoryImage.getImage());
        } else {
            holder.setDefaultImgCategoryImage();
        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

        private ImageView imgCategoryImage;

        public ViewHolder(View itemView) {
            super(itemView);
            imgCategoryImage = (ImageView) itemView.findViewById(R.id.img_category_image);

            imgCategoryImage.setOnClickListener(this);
        }

        public void setImgCategoryImage(String image) {
            Bitmap map = Base64Images.decode(image);
            if (map != null) {
                imgCategoryImage.setImageBitmap(map);
            } else {
                setDefaultImgCategoryImage();
            }
        }

        public void setDefaultImgCategoryImage() {
            Picasso.with(context)
                    .load(R.drawable.ic_not_found)
                    .into(imgCategoryImage);
        }

        @SuppressWarnings("deprecation")
        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}
