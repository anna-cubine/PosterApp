package com.example.posterapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PosterListener {
    private Button buttonAddToWatchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView postersRecyclerView = findViewById(R.id.postersRecyclerView);
        buttonAddToWatchList = findViewById(R.id.buttonAddToWatchlist);

        //prepare data

        List<Poster> posterList = new ArrayList<>();

        Poster memoriesOfMurder = new Poster();
        memoriesOfMurder.image = R.drawable.mem_of_murder_poster;
        memoriesOfMurder.name = "Memories of Murder";
        memoriesOfMurder.createdBy = "Bong Joon-Ho";
        memoriesOfMurder.desc = "In a small Korean province in 1986, two detectives struggle with the case of multiple young women being found raped and murdered by an unknown culprit.";
        memoriesOfMurder.rating = 5f;
        posterList.add(memoriesOfMurder);

        Poster parasite = new Poster();
        parasite.image = R.drawable.parasite_poster;
        parasite.name = "Parasite";
        parasite.createdBy = "Bong Joon-Ho";
        parasite.desc = "Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.";
        parasite.rating = 5f;
        posterList.add(parasite);

        Poster theRaid = new Poster();
        theRaid.image = R.drawable.the_raid_poster;
        theRaid.name = "The Raid Redemption";
        theRaid.createdBy = "Gareth Evans";
        theRaid.desc = "A S.W.A.T. team becomes trapped in a tenement run by a ruthless mobster and his army of killers and thugs.";
        theRaid.rating = 4f;
        posterList.add(theRaid);

        Poster chineseOdyssey = new Poster();
        chineseOdyssey.image = R.drawable.chinese_odyssey_poster;
        chineseOdyssey.name = "A Chinese Odyssey: Part One - Pandora's Box";
        chineseOdyssey.createdBy = "Jeffrey Lau";
        chineseOdyssey.desc = "A Monkey King is reincarnated in the un human form as Joker, a highwayman oblivious to his original identity and the fact that 500 years earlier, he and his master, the Longevity Monk, were punished and made to stay human.";
        chineseOdyssey.rating = 4f;
        posterList.add(chineseOdyssey);

        Poster shoplifters = new Poster();
        shoplifters.image = R.drawable.shoplifters_poster;
        shoplifters.name = "Shoplifters";
        shoplifters.createdBy = "Hirokazu Koreeda";
        shoplifters.desc = "On the margins of Tokyo, a dysfunctional band of outsiders are united by loyalty, a penchant for petty theft and playful grifting.";
        shoplifters.rating = 5f;
        posterList.add(shoplifters);

        Poster oldboy = new Poster();
        oldboy.image = R.drawable.oldboy_poster;
        oldboy.name = "Oldboy";
        oldboy.createdBy = "Park Chan-Wook";
        oldboy.desc = "After being kidnapped and imprisoned for fifteen years, Oh Dae-Su is released, only to find that he must track down his captor in five days.";
        oldboy.rating = 5f;
        posterList.add(oldboy);

        Poster policeStory = new Poster();
        policeStory.image = R.drawable.police_story_poster;
        policeStory.name = "Police Story";
        policeStory.createdBy = "Jackie Chan, Chi-Hwa Chen";
        policeStory.desc = "A virtuous Hong Kong Police Officer must clear his good name when the drug lord he is after frames him for the murder of a dirty cop.";
        policeStory.rating = 4f;
        posterList.add(policeStory);

        Poster killZone = new Poster();
        killZone.image = R.drawable.kill_zone_poster;
        killZone.name = "Kill Zone";
        killZone.createdBy = "Wilson Yip";
        killZone.desc = "A near retired inspector and his unit are willing to put down a crime boss at all costs while dealing with his replacement, who is getting in their way";
        killZone.rating = 3f;
        posterList.add(killZone);

        Poster crouchingTiger = new Poster();
        crouchingTiger.image = R.drawable.crouching_tiger_poster;
        crouchingTiger.name = "Crouching Tiger, Hidden Dragon";
        crouchingTiger.createdBy = "Ang Lee";
        crouchingTiger.desc = "A young Chinese warrior steals a sword from a famed swordsman and then escapes into a world of romantic adventure with a mysterious man in the frontier of the nation.";
        crouchingTiger.rating = 3f;
        posterList.add(crouchingTiger);

        Poster badGenius = new Poster();
        badGenius.image = R.drawable.bad_genius_poster;
        badGenius.name = "Bad Genius";
        badGenius.createdBy = "Baz Poonpiriya";
        badGenius.desc = "Lynn, a genius high school student who makes money by cheating tests, receives a new task that leads her to set foot on Sydney, Australia.";
        badGenius.rating = 4f;
        posterList.add(badGenius);


        final PosterAdapter posterAdapter = new PosterAdapter(posterList, this);
        postersRecyclerView.setAdapter(posterAdapter);
        /**
         * Watchlist button listener, adds selected posters to a watchlist and displays a toast
         * to notify the user that the movies have been added.
         */
        buttonAddToWatchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Poster> selectPosters = posterAdapter.getSelectedPosters();

                StringBuilder posterNames = new StringBuilder();
                for (int i = 0; i < selectPosters.size(); i++) {
                    if (i == 0) {
                        posterNames.append(selectPosters.get(i).name);
                    } else {
                        posterNames.append("\n").append(selectPosters.get(i).name);
                    }
                }
                Toast.makeText(MainActivity.this, posterNames.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Checks whether the poster card is selected and if it is, shows the add to watchlist button
     * @param isSelected
     */
    @Override
    public void onPosterAction(Boolean isSelected) {
        if(isSelected){
            buttonAddToWatchList.setVisibility(View.VISIBLE);
            System.out.println("Poster(s) selected");
        } else {
            buttonAddToWatchList.setVisibility(View.GONE);
            System.out.println("No poster selected");
        }
    }
}