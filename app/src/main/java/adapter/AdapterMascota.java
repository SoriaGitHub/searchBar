package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bryansoria.searchbar.R;

import org.w3c.dom.Text;

import java.util.List;

import object.Mascota;

public class AdapterMascota extends RecyclerView.Adapter <AdapterMascota.viewholdermascotas>{

    List<Mascota> mascotaList;

    public AdapterMascota(List<Mascota> mascotaList) { //Constructor que sirve para llamar la informacion desde el mainActivity

        this.mascotaList = mascotaList;
    }

    @NonNull
    @Override
    public viewholdermascotas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mascotas,parent,false);
        viewholdermascotas holder = new viewholdermascotas(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholdermascotas holder, int position) {

        Mascota ms = mascotaList.get(position);
        holder.tv_nombre.setText(ms.getNombre());
        holder.tv_edad.setText(ms.getEdad());
        holder.tv_seo.setText(ms.getSeo());
    }

    @Override
    public int getItemCount() { //Nos registra el numero de registros dentro del adaptador

        return mascotaList.size();
    }

    public class viewholdermascotas extends RecyclerView.ViewHolder {

        TextView tv_nombre,tv_edad,tv_seo;

        public viewholdermascotas(@NonNull View itemView) {
            super(itemView);
            tv_nombre = itemView.findViewById(R.id.tv_nombre);
            tv_edad = itemView.findViewById(R.id.tv_edad);
            tv_seo = itemView.findViewById(R.id.tv_seo);
        }
    }
}
