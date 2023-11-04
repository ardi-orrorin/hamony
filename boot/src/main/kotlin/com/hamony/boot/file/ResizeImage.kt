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

    var scale: Double = 0.0

    lateinit var bImage: BufferedImage

    fun read(file: File): ResizeImage {
        this.bImage = ImageIO.read(file)
        return this
    }

    fun scale(size: Int, criteria: ResizeCriteria ): ResizeImage {
        if(this.bImage == null) {
            throw Exception("이미지를 먼저 읽어주세요.")
        }
        if(criteria == ResizeCriteria.HEIGHT) {
            this.scale = size / bImage.getHeight().toDouble()
        } else if(criteria == ResizeCriteria.WIDTH) {
            this.scale = size / bImage.getWidth().toDouble()
        } else {
            throw Exception("잘못된 기준 입니다.")
        }
        return this
    }

    fun thumnail(file: File, ext: String): ByteArray{

        val oWidth: Int = (bImage.getWidth().toDouble() * this.scale).toInt()
        val oHeight: Int = (bImage.getHeight().toDouble() * this.scale).toInt()

        val bufImage: BufferedImage = BufferedImage(oWidth, oHeight, BufferedImage.TYPE_3BYTE_BGR)

        val grapic: Graphics2D = bufImage.createGraphics()

        val image: Image = bImage.getScaledInstance(oWidth, oHeight, Image.SCALE_SMOOTH)

        grapic.drawImage(image, 0, 0, oWidth, oHeight, null)
        grapic.dispose()

        val baos: ByteArrayOutputStream = ByteArrayOutputStream()
        ImageIO.write(bufImage, ext, baos)

        return baos.toByteArray()
    }


}