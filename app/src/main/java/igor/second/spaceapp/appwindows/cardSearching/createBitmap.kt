package igor.second.spaceapp.appwindows.cardSearching

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import androidx.core.graphics.createBitmap

internal fun createDirectionArrowBitmap(): Bitmap {
    val size = 200
    val bitmap = createBitmap(size, size)
    val canvas = Canvas(bitmap)

    val paint = Paint().apply {
        style = Paint.Style.FILL
    }

    val path = Path().apply {
        moveTo(size / 2f, 0f)
        lineTo(size.toFloat(), size.toFloat())
        lineTo(size / 2f, size * 0.7f)
        lineTo(0f, size.toFloat())
        close()
    }

    canvas.drawPath(path, paint)
    return bitmap
}