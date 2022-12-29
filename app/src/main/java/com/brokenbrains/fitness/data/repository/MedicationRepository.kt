package com.brokenbrains.fitness.data.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.brokenbrains.fitness.data.model.medication.MedicationModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MedicationRepository @Inject constructor(
    private val db: FirebaseFirestore,
    private val authRepository: AuthRepository
) {

    fun addNewMedication(
        medication: MedicationModel
    ) {
        db.collection("users")
            .document(authRepository.currentUser!!.uid)
            .collection("medications")
            .document(medication.uuid)
            .set(medication)
            .addOnSuccessListener {
                Log.d(
                    this::class.simpleName,
                    "DocumentSnapshot added with ID: ${medication.uuid}"
                )
            }.addOnFailureListener { e ->
                Log.w(this::class.simpleName, "Error adding document $e")
            }
    }

    suspend fun getMedicationById(id: Int): Flow<MedicationModel> {
        var medicationModel = MedicationModel()
        return try {
            flow {
                db.collection("users")
                    .document(authRepository.currentUser!!.uid)
                    .collection("medications")
                    .document(id.toString())
                    .get()
                    .addOnSuccessListener { document ->
                        medicationModel = document.toObject(medicationModel::class.java)!!
                    }
                emit(medicationModel)
            }
        } catch (e: Exception) {
            flow {
                emit(medicationModel)
            }
        }
    }


    @WorkerThread
    fun getAllMedications(): Flow<List<MedicationModel>> = callbackFlow {
        val medications = mutableListOf<MedicationModel>()
        db.collection("users")
            .document(authRepository.currentUser!!.uid)
            .collection("medications").orderBy("startAt")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    medications.add(document.toObject(MedicationModel::class.java))
                }
                Log.d(this::class.simpleName, "DocumentSnapshot added with ID: $medications")
                trySend(medications).isSuccess
            }.addOnFailureListener { e ->
                Log.w(this::class.simpleName, "Error getting documents $e")
                cancel()
            }
        try {
            awaitClose {
                channel.close()
            }
        } catch (e: Exception) {
            Log.w(this::class.simpleName, "Error getting documents $e")
            cancel()
        }
    }

    @WorkerThread
    fun deleteMedication(uuid: String) = callbackFlow {
        db.collection("users")
            .document(authRepository.currentUser!!.uid)
            .collection("medications")
            .document(uuid)
            .delete()
            .addOnSuccessListener {
                Log.d(this::class.simpleName, "DocumentSnapshot successfully deleted!")
                trySend(true).isSuccess
            }
            .addOnFailureListener { e ->
                Log.w(this::class.simpleName, "Error deleting document $e")
                trySend(false).isSuccess
            }
        try {
            awaitClose {
                channel.close()
            }
        } catch (e: Exception) {
            Log.w(this::class.simpleName, "Error deleting document $e")
            cancel()
        }
    }


}