package com.example.practicafinalpdm.adaptadores;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicafinalpdm.R;
import com.example.practicafinalpdm.modelos.Artista;
import com.example.practicafinalpdm.modelos.Exposicion;

import java.util.ArrayList;

public class AdaptadorArtista extends RecyclerView.Adapter<AdaptadorArtista.HolderArtista>{


    public class HolderArtista extends RecyclerView.ViewHolder
    {

        TextView nombreArtista;
        TextView paisArtista;

        HolderArtista(View itemView)
        {
         /* En el constructor obtendremos los recursos del fichero
         de recursos xml que tengamos asociado a la clase, en
         este caso el fichero listitem_titular.xml
         */
            super(itemView);

            nombreArtista = itemView.findViewById(R.id.etNombreArtista);
            paisArtista = itemView.findViewById(R.id.etPaisArtista);

            // Si hubiera que sobrecargar eventos se haria aqui

        }

        public void removeItem(int position)
        {
            listaArtistas.remove(position);
            notifyItemRemoved(position);
        }
    };




    private Context contexto;
    protected View.OnClickListener onClickListener;
    private ArrayList<Artista> listaArtistas;

    public AdaptadorArtista(Context contexto, ArrayList<Artista> arrayentrante)
    {
        this.contexto = contexto;
        this.listaArtistas = arrayentrante;
    }

    /**
     * Cuando clicamos en el hijo de un recview
     * @param onClickListener
     */
    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    /**
     * Agrega los datos que queramos mostrar
     * @param arrayentrante Datos a mostrar, en este caso, titulares
     */
    public void add(ArrayList<String> arrayentrante)
    {
        arrayentrante.clear();
        arrayentrante.addAll(arrayentrante);
    }
    /**
     * Actualiza los datos del ReciclerView
     */
    public void refrescar()
    {
        notifyDataSetChanged();
    }

    @Override
    public HolderArtista onCreateViewHolder(ViewGroup parent, int viewType)//encargado de crear los nuevos objetos ViewHolder necesarios para los elementos de la coleccion
    {
        //vale esto se limita a inflar nuestra vista con el layout del item del recyclerView, y a crear y devolver un nuevo ViewHolder llamando al
        //constructor de nuestra clase HolderArtista pasandole dicha vista como parametro.
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerartista, parent, false);
        v.setOnClickListener(onClickListener);
        HolderArtista pvh = new HolderArtista(v);
        return pvh;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final HolderArtista itemActual, final int position)
    {//actualiza los datos de un ViewHolder ya existente
        //

        itemActual.nombreArtista.setText(listaArtistas.get(position).getNombre());
        itemActual.paisArtista.setText(listaArtistas.get(position).getPais());

        //no se si esto esta bien
        //vale aqui habria que sacar el id del artista para poder sacar el artista que ha estado en esa exposicion
        //itemActual.nombreArtista.setText(listaExpo.get(position).)
    }

    @Override
    public int getItemCount()
    {
        return listaArtistas.size();
    }//simplemente indica el numero de elementos que va a tener el recyclerView
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

