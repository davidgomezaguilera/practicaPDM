package com.example.practicafinalpdm.adaptadores;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicafinalpdm.R;
import com.example.practicafinalpdm.controller.BDExposiciones;
import com.example.practicafinalpdm.modelos.Exposicion;

import java.util.ArrayList;

public class AdaptadorExpo extends RecyclerView.Adapter<AdaptadorExpo.HolderExpo>{


    public class HolderExpo extends RecyclerView.ViewHolder
    {

        TextView descripcionExpo;
        Button deleteExpo;
        TextView nombreExpo;

        HolderExpo(View itemView)
        {
         /* En el constructor obtendremos los recursos del fichero
         de recursos xml que tengamos asociado a la clase, en
         este caso el fichero listitem_titular.xml
         */
            super(itemView);

            nombreExpo = itemView.findViewById(R.id.nombreExpo);
            descripcionExpo = itemView.findViewById(R.id.descripcionExpo);
            deleteExpo = itemView.findViewById(R.id.btDeleteExpo);
            // Si hubiera que sobrecargar eventos se haria aqui

        }

        public void removeItem(int position)
        {
            listaExpo.remove(position);
            notifyItemRemoved(position);
        }
    };




    private Context contexto;
    protected View.OnClickListener onClickListener;
    private ArrayList<Exposicion> listaExpo;

    public AdaptadorExpo(Context contexto, ArrayList<Exposicion> arrayentrante)
    {
        this.contexto = contexto;
        this.listaExpo = arrayentrante;
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
    public HolderExpo onCreateViewHolder(ViewGroup parent, int viewType)//encargado de crear los nuevos objetos ViewHolder necesarios para los elementos de la coleccion
    {
        //vale esto se limita a inflar nuestra vista con el layout del item del recyclerView, y a crear y devolver un nuevo ViewHolder llamando al
        //constructor de nuestra clase HolderExpo pasandole dicha vista como parametro.
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerexpo, parent, false);
        v.setOnClickListener(onClickListener);
        HolderExpo pvh = new HolderExpo(v);
        return pvh;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final HolderExpo itemActual, final int position)
    {//actualiza los datos de un ViewHolder ya existente
        //

        itemActual.nombreExpo.setText(listaExpo.get(position).getNombreExpo());
        itemActual.descripcionExpo.setText(listaExpo.get(position).getDescripcion());
        itemActual.deleteExpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //futura borrar exposicion
                BDExposiciones bd = new BDExposiciones(contexto);

                bd.deleteExpo(listaExpo.get(position).getIdExposicion());
                Toast.makeText(contexto, "Exposicion borrada con exito", Toast.LENGTH_LONG);
                new AdaptadorExpo.HolderExpo(v).removeItem(position);
            }
        });
        //no se si esto esta bien
        //vale aqui habria que sacar el id del artista para poder sacar el artista que ha estado en esa exposicion
        //itemActual.nombreArtista.setText(listaExpo.get(position).)
    }

    @Override
    public int getItemCount()
    {
        return listaExpo.size();
    }//simplemente indica el numero de elementos que va a tener el recyclerView
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

