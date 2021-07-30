package com.example.empresas.extensions

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.os.StrictMode
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import androidx.core.content.FileProvider





fun Fragment.shareCompany(description: String, mUrlImage: String) {
    StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder().build())

    Picasso.get().load(mUrlImage).into(object : Target {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                description
            )
            shareIntent.putExtra(Intent.EXTRA_STREAM, getLocalBitmpaUri(bitmap!!, requireContext()))
            shareIntent.type = ("image/*")
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(Intent.createChooser(shareIntent, "Enviar essa empresa pra quem?"))
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
    })


}

private fun getLocalBitmpaUri(bitmap: Bitmap, context: Context): Uri? {
    var bmpUri: Uri? = null
    try {
        val file = File(
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "share_image_${System.currentTimeMillis()}.png"
        )
        val out = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)
        out.close()
        bmpUri = FileProvider.getUriForFile(
            context,
            context.applicationContext.packageName.toString() + ".provider",
            file
        )
    } catch (io: IOException) {
        io.printStackTrace()
    }

    return bmpUri
}