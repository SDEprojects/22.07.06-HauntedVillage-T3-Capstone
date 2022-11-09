package com.game.HauntedVillage;


import com.apps.util.Console;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Sound {

    private Boolean SFX_On = true;
    private Boolean musicOn = true;
    private double musicLevel = 1;
    private double soundFXLevel = 1;
    private String musicOnOrOff;
    private String sfxOnOrOff;
    private Art art = new Art();
    private File musicFile = new File("resources/music.wav");
    private File soundFXFile = new File("resources/inventorySFX.wav");

//    public static void musicPlayer(File file) {
//        if(getMusicOn()){
//            try {
//                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
//                Clip clip = AudioSystem.getClip();
//                clip.open(audioStream);
//                clip.start();
//                FloatControl gainMusicControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//                gainMusicControl.setValue(20f * (float) Math.log10(getMusicLevel())); // set volume to 50% to start
//
//            } catch (UnsupportedAudioFileException e) {
//                throw new RuntimeException(e);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            } catch (LineUnavailableException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    public static void soundFXPlayer(File file) {
//        if(getSFX_On()){
//            try {
//                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
//                Clip clip = AudioSystem.getClip();
//                clip.open(audioStream);
//                clip.start();
//                FloatControl gainMusicControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//                gainMusicControl.setValue(20f * (float) Math.log10(getSoundFXLevel())); // set volume to 50% to start
//
//            } catch (UnsupportedAudioFileException e) {
//                throw new RuntimeException(e);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            } catch (LineUnavailableException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    public static void musicMenu() {
//        Scanner scanner = new Scanner(System.in);
//
//        while (!answer.equals("C")) {
//            if (getSFX_On()) {
//                System.out.println("Music Options: C = Continue with music, V = Change Volume, Q = Continue without music, S = Turn Off Sound Effects");
//                System.out.println("Enter your choice:");
//            } else {
//                System.out.println("Music Options: C = Continue with music, V = Change Volume, Q = Continue without music, S = Turn On Sound Effects");
//                System.out.println("Enter your choice:");
//            }
//            answer = scanner.nextLine();
//            answer= answer.toUpperCase();
//        }
//        switch(answer){
//            case ("C"):
//                break;
//            case ("V"):
//                System.out.println("What would you like to set the music volume to? (0 to 100) ");
//                Scanner volScanner = new Scanner(System.in);
//                double volumeEntry = 0;
//                double SFXEntry = 0;
//                volumeEntry = Float.parseFloat(volScanner.next());
//                if (volumeEntry <= 100 && volumeEntry >=0){
//                    setMusicLevel(volumeEntry);
//                } else {
//                    System.out.println("Not valid entry. Please enter a value between 0 and 100.");
//                }
//                SFXEntry = Float.parseFloat(volScanner.next());
//                System.out.println("What would you like to set the sound effects volume to? (0 to 100)");
//                if (SFXEntry <= 100 && SFXEntry >=0){
//                    setSoundFXLevel(SFXEntry);
//                } else {
//                    System.out.println("Not valid entry. Please enter a value between 0 and 100.");
//                }
//                break;
//            case ("Q"):
//                setMusicOn(false);
//                answer = "C";
//                break;
//            case ("S"):
//                setSFX_On(false);
//                break;
//            default:
//                System.out.println("not a valid response");
//        }
//        System.out.println("left music menu...");
//        Console.pause(1000);
//
//    }


    public Sound() {
    }

    public void runMusic(){

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
            FloatControl gainSfxControl = (FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainSfxControl.setValue(20f * (float) Math.log10(getSoundFXLevel())); // set volume to 50% to start

            String response = "";

            while (!response.equals("C")) {
                System.out.println(art.showArt("music_note"));
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
                                    System.out.println("Not a valid entry. Please enter a value between 0 and 100.");
                                }
                            }
                        }
                        while(!validSFX) {
                            System.out.println("The current SFX volume is " + (float) (Math.pow(10f, gainSfxControl.getValue()/20f))*50 +"/100" );
                            System.out.println("What would you like to set the SFX volume to? ");
                            Scanner SFXScanner = new Scanner(System.in);
                            String sfxInput = SFXScanner.next();
                            if(sfxInput.matches(regex)) {
                                float sfxEntry = 0;
                                sfxEntry = Float.parseFloat(sfxInput);
                                if (sfxEntry <= 100 && sfxEntry >=0){
                                    setSoundFXLevel(sfxEntry);
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
                        setSFX_On(!getSFX_On());
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

    public void runFX(){

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

    public String musicStatus(){
        if (getMusicOn()){
            setMusicOnOrOff("Toggle music OFF");
        }
        else {
            setMusicOnOrOff("Toggle music ON");
        }
        return musicOnOrOff;
    }

    public String sfxStatus(){
        if(getSFX_On()) {
            setSfxOnOrOff("Toggle SFX OFF");
        }
        else {
            setSfxOnOrOff("Toggle SFX ON");
        }
        return sfxOnOrOff;
    }

    // ACCESSOR METHODS
    private double getMusicLevel() {
        return musicLevel;
    }

    private double getSoundFXLevel() {
        return soundFXLevel;
    }

    private Boolean getMusicOn() {
        return musicOn;
    }

    private Boolean getSFX_On() {
        return SFX_On;
    }

    private File getMusicFile() {
        return musicFile;
    }

    private File getSoundFXFile() {
        return soundFXFile;
    }

    private void setMusicOnOrOff(String musicOnOrOff) {
        this.musicOnOrOff = musicOnOrOff;
    }

    private void setSfxOnOrOff(String sfxOnOrOff) {
        this.sfxOnOrOff = sfxOnOrOff;
    }

    private void setSFX_On(Boolean SFX_On) {
        this.SFX_On = SFX_On;
    }

    private void setMusicOn(Boolean musicOn) {
        this.musicOn = musicOn;
    }

    private void setMusicLevel(double musicLevel) {
        this.musicLevel = musicLevel;
    }

    private void setSoundFXLevel(double soundFXLevel) {
        this.soundFXLevel = soundFXLevel;
    }
}