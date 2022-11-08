package com.game.HauntedVillage;


import com.apps.util.Console;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Sound {

    private static Boolean SFX_On = true;
    private static Boolean musicOn = true;
    private static double musicLevel = 1;
    private static double soundFXLevel = 1;
    static String answer = "";
    private static String musicOnOrOff;
    private static String sfxOnOrOff;
    Clip clip = AudioSystem.getClip();

    private static File musicFile = new File("resources/music.wav");
    private static File soundFXFile = new File("resources/inventorySFX.wav");

    public Sound() throws LineUnavailableException {
    }

    public void musifOff() {
    musicOn = false;
    if (getMusicOn().equals(musicOn)) {
        clip.stop();
        setMusicOn(false);
    }
}


    public static void runMusic(){

//        File file = new File("resources/music.wav");

        Console.clear();

        try {
            Scanner scanner = new Scanner(System.in);

            //background music
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(getMusicFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(10);
            FloatControl gainMusicControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainMusicControl.setValue(20f * (float) Math.log10(getMusicLevel())); // set volume to 50% to start

            //sfx
            AudioInputStream sfxStream = AudioSystem.getAudioInputStream(getSoundFXFile());
            Clip sfxClip = AudioSystem.getClip();
            sfxClip.open(sfxStream);
//            sfxClip.start();
//            sfxClip.loop(10);
            FloatControl gainSfxControl = (FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainMusicControl.setValue(20f * (float) Math.log10(getSoundFXLevel())); // set volume to 50% to start

            String response = "";

            while (!response.equals("C")) {
                Art.showArt("music_note");
                System.out.println("Music Options: C = Continue with current settings, V = Change Volume, Q = "+ musicStatus() +", S = "+sfxStatus());
                System.out.println("The current music volume is " + (float) (Math.pow(10f, gainMusicControl.getValue()/20f))*50 +"/100" );
                System.out.println("The current SFX volume is " + (float) (Math.pow(10f, gainSfxControl.getValue()/20f))*50 +"/100" );
                System.out.println("Enter your choice:");


                response = scanner.next();
                response = response.toUpperCase();

                switch(response){
                    case ("C"):
                    break;
                    case ("V"):
                        boolean validVol = false;
                        boolean validSFX = false;
                        String regex = "\\d+";
                        while(!validVol) {
                            System.out.println("The current music volume is " + (float) (Math.pow(10f, gainMusicControl.getValue()/20f))*50 +"/100" );
                            System.out.println("What would you like to set the volume to? ");
                            Scanner volScanner = new Scanner(System.in);
                            String volInput = volScanner.next();
                            if(volInput.matches(regex)) {
                                float volumeEntry = 0;
                                volumeEntry = Float.parseFloat(volInput);
                                if (volumeEntry <= 100 && volumeEntry >=0){
                                    setMusicLevel(volumeEntry);
                                    gainMusicControl.setValue(20f * (float) Math.log10(2*(getMusicLevel()/100)));
                                    validVol = true;
                                }
                                else {
                                    System.out.println("Not valid entry. Please enter a value between 0 and 100.");
                                }
                            }
                        }
                        while(!validSFX) {
                            System.out.println("The current SFX volume is " + (float) (Math.pow(10f, gainSfxControl.getValue()/20f))*50 +"/100" );
                            System.out.println("What would you like to set the SFX volume to? ");
                            Scanner SFXScanner = new Scanner(System.in);
                            String sfxInput = SFXScanner.next();
                            if(sfxInput.matches(regex)) {
                                float SFXEntry = 0;
                                SFXEntry = Float.parseFloat(sfxInput);
                                if (SFXEntry <= 100 && SFXEntry >=0){
                                    setSoundFXLevel(SFXEntry);
                                    gainSfxControl.setValue(20f * (float) Math.log10(2*(getSoundFXLevel()/100)));
                                    validSFX = true;
                                }
                                else {
                                    System.out.println("Not valid entry. Please enter a value between 0 and 100.");
                                }
                            }
                        }
                        break;
                    case ("Q"):
                        if (getMusicOn()){
                            clip.stop();
                            setMusicOn(false);
                        } else{
                         setMusicOn(true);
                         clip.start();
                        }
                        break;
                    case ("S"):
                        if (getSFX_On()){
                            setSFX_On(false);
                        } else {
                            setSFX_On(true);
                        }
                        break;
                    default:
                        System.out.println("not a valid response");
                }
                Console.clear();
            }
        } catch (UnsupportedAudioFileException e) {
            System.out.println("runMusic() UnsupportedAudioFileException");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("runMusic() IOException");
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public static void runFX(){

        if (getSFX_On()){
            try {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(getSoundFXFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();

            } catch (UnsupportedAudioFileException e) {
                System.out.println("runMusic() UnsupportedAudioFileException");
                throw new RuntimeException(e);
            } catch (IOException e) {
                System.out.println("runMusic() IOException");
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String musicStatus(){
        if (getMusicOn()){
            musicOnOrOff = "Toggle music OFF";
        } else {
            musicOnOrOff = "Toggle music ON";
        }
        return musicOnOrOff;
    }

    public static String sfxStatus(){
        if(getSFX_On()) {
            sfxOnOrOff = "Toggle SFX OFF";
        } else {
            sfxOnOrOff = "Toggle SFX ON";
        }
        return sfxOnOrOff;
    }

    // ACCESSOR METHODS
    public static double getMusicLevel() {
        return musicLevel;
    }

    public static void setMusicLevel(double musicLevel) {
        Sound.musicLevel = musicLevel;
    }

    public static double getSoundFXLevel() {
        return soundFXLevel;
    }

    public static void setSoundFXLevel(double soundFXLevel) {
        Sound.soundFXLevel = soundFXLevel;
    }

    public static Boolean getMusicOn() {
        return musicOn;
    }

    public static void setMusicOn(Boolean musicOn) {
        Sound.musicOn = musicOn;
    }

    public static Boolean getSFX_On() {
        return SFX_On;
    }

    public static void setSFX_On(Boolean SFX_On) {
        Sound.SFX_On = SFX_On;
    }

    public static File getMusicFile() {
        return musicFile;
    }

    public static File getSoundFXFile() {
        return soundFXFile;
    }
}