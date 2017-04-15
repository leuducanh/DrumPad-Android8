package techkid.leu.drumpad;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by l on 4/11/2017.
 */

public class SoundManager {
    public static int NUMBER_OF_NOTES = 16;

    public static SoundPool soundPool = new SoundPool(NUMBER_OF_NOTES, AudioManager.STREAM_MUSIC,0);

    public static ArrayList<Integer> soundIds;
    public static void loadSoundsIntoList(Context context) {
        soundIds = new ArrayList<>();
        for(int i = 1;i <= NUMBER_OF_NOTES;i++){
            int resIdSound = context.getResources().getIdentifier("s"+i
                    ,"raw"
                    ,context.getPackageName());

            int soundPoolId = soundPool.load(context,resIdSound,1);
            soundIds.add(soundPoolId);
        }
    }

    public static HashMap<String,Integer> listSoundId = new HashMap<>();

    static {
        listSoundId.put("s1",0);
        listSoundId.put("s2",1);
        listSoundId.put("s3",2);
        listSoundId.put("s4",3);
        listSoundId.put("s5",4);
        listSoundId.put("s6",5);
        listSoundId.put("s7",6);
        listSoundId.put("s8",7);
        listSoundId.put("s9",8);
        listSoundId.put("s10",9);
        listSoundId.put("s11",10);
        listSoundId.put("s12",11);
        listSoundId.put("s13",12);
        listSoundId.put("s14",13);
        listSoundId.put("s15",14);
        listSoundId.put("s16",15);
    }

    public static void playSound(String s){
        soundPool.play(soundIds.get(listSoundId.get("s"+s)),1f,1f,1,0,1.0f);
    }
}
