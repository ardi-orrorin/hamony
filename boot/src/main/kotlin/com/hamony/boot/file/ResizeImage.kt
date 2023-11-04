package com.hamony.boot.file

import org.springframework.stereotype.Component
import java.awt.Graphics2D
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import javax.imageio.ImageIO

@Component
class ResizeImage{

    var scale: Double? = null

    var bImage: BufferedImage? = null

    fun read(file: File): ResizeImage {
        this.bImage = ImageIO.read(file)
        return this
    }

    fun scale(size: Int, criteria: ResizeCriteria ): ResizeImage {
        if(this.bImage == null) {
            throw Exception("이미지를 먼저 읽어주세요.")
        }

        fun validOverScale(tempScale: Double): Double {
            if (tempScale >= 1.0) {
                return 1.0
            } else {
                return tempScale
            }
        }

        when (criteria) {
            ResizeCriteria.HEIGHT ->
                this.scale = validOverScale(size / bImage!!.getHeight().toDouble())
            ResizeCriteria.WIDTH ->
                this.scale = validOverScale(size / bImage!!.getWidth().toDouble())
            else -> throw Exception("잘못된 기준 입니다.")
        }
        return this
    }

    private fun resize(): BufferedImage {
        if (bImage == null) {
            throw Exception("이미지를 먼저 읽어주세요.")
        } else if (scale == null) {
            throw Exception("비율을 먼저 입력하세요.")
        }

        val oWidth: Int = (bImage!!.getWidth().toDouble() * this.scale as Double).toInt()
        val oHeight: Int = (bImage!!.getHeight().toDouble() * this.scale as Double).toInt()

        val bufImage = BufferedImage(oWidth, oHeight, BufferedImage.TYPE_3BYTE_BGR)

        val graphic: Graphics2D = bufImage.createGraphics()

        val image: Image = bImage!!.getScaledInstance(oWidth, oHeight, Image.SCALE_FAST)

        graphic.drawImage(image, 0, 0, oWidth, oHeight, null)
        graphic.dispose()

        return bufImage
    }

    fun readThumnail(ext: String): ByteArray {
        val bufImage: BufferedImage = resize()

        val baos: ByteArrayOutputStream = ByteArrayOutputStream()
        ImageIO.write(bufImage, ext, baos)

        return baos.toByteArray()
    }

    fun writeThumnail(file: File, ext: String): Boolean {
        val bufImage: BufferedImage = resize()

        if (!file.isDirectory)
            file.mkdirs()

        ImageIO.write(bufImage, ext, file)

        return true
    }

}