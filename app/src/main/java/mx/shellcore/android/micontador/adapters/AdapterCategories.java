package mx.shellcore.android.micontador.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mx.shellcore.android.micontador.R;
import mx.shellcore.android.micontador.model.Category;

public class AdapterCategories extends RecyclerView.Adapter<AdapterCategories.ViewHolder> {

    private ArrayList<Category> categories;
    private Context context;

    OnItemClickListener onItemClickListener;

    public AdapterCategories(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_category, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Category category = categories.get(position);

        holder.setTxtCategory(category.getName());
        holder.setImgCategory(category.getLogo().getImage());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgCategory;
        private TextView txtCategory;

        public ViewHolder(View itemView) {
            super(itemView);

            imgCategory = (ImageView) itemView.findViewById(R.id.img_category);
            txtCategory = (TextView) itemView.findViewById(R.id.txt_category);

            itemView.setOnClickListener(this);
        }

        public void setImgCategory(String imgUrl) {
            imgCategory.setImageURI(Uri.parse(imgUrl));
        }

        public void setTxtCategory(String category) {
            txtCategory.setText(category);
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
