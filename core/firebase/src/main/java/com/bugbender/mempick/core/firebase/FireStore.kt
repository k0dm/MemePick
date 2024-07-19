package com.bugbender.mempick.core.firebase

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface FireStore {

    suspend fun add(collection: String, data: HashMap<String, Any>): String

    suspend fun addToSub(
        collection: String,
        document: String,
        subCollection: String,
        data: HashMap<String, Any>
    ): String

    suspend fun read(collection: String): List<Map<String, Any>>

    suspend fun deleteDocument(collection: String, document: String)

    suspend fun deleteSubDocument(
        collection: String,
        document: String,
        subCollection: String,
        subDocument: String
    )

    class Base @Inject constructor() : FireStore {

        private val db = Firebase.firestore


        override suspend fun add(collection: String, data: HashMap<String, Any>): String {
            return suspendCoroutine { continuation ->
                db.collection(collection)
                    .add(data)
                    .addOnSuccessListener { documentReference ->
                        continuation.resume(documentReference.id)
                    }
                    .addOnFailureListener { e ->
                        continuation.resumeWithException(e)
                    }
            }
        }

        override suspend fun addToSub(
            collection: String,
            document: String,
            subCollection: String,
            data: HashMap<String, Any>
        ): String {
            return suspendCoroutine { continuation ->
                db.collection(collection).document(document).collection(subCollection)
                    .add(data)
                    .addOnSuccessListener { documentReference ->
                        continuation.resume(documentReference.id)
                    }
                    .addOnFailureListener { e ->
                        continuation.resumeWithException(e)
                    }
            }
        }


        override suspend fun read(collection: String):
                List<Map<String, Any>> {
            return suspendCoroutine { continuation ->
                db.collection(collection)
                    .get()
                    .addOnSuccessListener { querySnapshot ->
                        continuation.resume(querySnapshot.documents.map { it.data ?: mapOf() })
                    }
                    .addOnFailureListener { e ->
                        continuation.resumeWithException(e)
                    }
            }
        }

        override suspend fun deleteDocument(collection: String, document: String) {
            return suspendCoroutine { continuation ->
                db.collection(collection).document(document)
                    .delete()
                    .addOnSuccessListener {
                        continuation.resume(Unit)
                    }
                    .addOnFailureListener { e ->
                        continuation.resumeWithException(e)
                    }
            }
        }

        override suspend fun deleteSubDocument(
            collection: String,
            document: String,
            subCollection: String,
            subDocument: String
        ) {
            return suspendCoroutine { continuation ->
                db.collection(collection).document(document).collection(subCollection)
                    .document(subDocument)
                    .delete()
                    .addOnSuccessListener {
                        continuation.resume(Unit)
                    }
                    .addOnFailureListener { e ->
                        continuation.resumeWithException(e)
                    }
            }
        }
    }
}