package pe.edu.upc.casip.casip_backend.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import pe.edu.upc.casip.casip_backend.models.Orden;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class OrdenRepository {

    public String guardarOrden(Orden orden) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = db.collection("ordenes").document(orden.getIdOrden()).set(orden);

        return future.get().getUpdateTime().toString();
    }

    public List<Orden> obtenerTodasLasOrdenes() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("ordenes").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Orden> listaOrdenes = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            listaOrdenes.add(document.toObject(Orden.class));
        }

        return listaOrdenes;
    }

    public Orden obtenerOrdenPorId(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentSnapshot document = db.collection("ordenes").document(id).get().get();

        if (document.exists()) {
            return document.toObject(Orden.class);
        }
        return null;
    }

    public String actualizarOrden(String id, Orden orden) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        orden.setIdOrden(id);

        ApiFuture<WriteResult> future = db.collection("ordenes").document(id).set(orden);

        return future.get().getUpdateTime().toString();
    }
}