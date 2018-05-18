package com.example.androidquiz;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class StartActivity extends AppCompatActivity {

    private TextView countLabel;
    private TextView questionLabel;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;

    private String correctAnswer;
    private String answerDescription;
    private int correctAnswerCount = 0;
    private int quizCount = 1;
    static final private int totalQuestions = 10;


    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countLabel = findViewById(R.id.countLabel);
        questionLabel = findViewById(R.id.questionLabel);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);


        //Receive quizMode from MainActivity
        int quizMode = getIntent().getIntExtra("QUIZ_MODE", 0);
        Log.v("MODE_OPTIONS", quizMode + "");

        //Create quizArray from quizData
        if (quizMode <= 1) {
            String quizData[][] = {
                    //{"Question", "Answer", "Choice1", "Choice2", "Choice3", Answer Description}
                    {"Which Planet is nearest to the Sun?", "Mercury", "Earth", "Mars", "Venus", "Mercury is the closest planet to the Sun, follower by Venus, Earth, then Mars."},
                    {"Who was the first person to set foot on the Moon?", "Neil Armstrong", "Lance Armstrong", "Toby Mcquire", "Albert Einstein", "Neil Armstrong was the first person to set foot on the moon, followed by Buzz Aldrin on the Apollo 11 mission on July 20, 1969. The third member, Michael Collins, stayed in orbit."},
                    {"How long does it take for light to travel from the Sun to Earth?", "Approximately 8 minutes", "Approximately 37 minutes", "Approximately 8 hours", "Aproximately 24 hours", "It takes light 8 minutes and 20 seconds to reach Earth from the Sun. That means that the light from the Sun we see is from 8 minutes and 20 seconds ago. If the Sun were to do suddenly disappear, we wouldn't notice for approximately 8 minutes"},
                    {"What planet in the Solar System is the closest in size to Earth?", "Venus", "Mars", "Pluto", "Mercury", "Venus and Earth are very similar is terms of mass and size, however they are vastly different. One day on Venus counts for 243 Earth days."},
                    {"What are exoplanets?", "Planets outside of the Solar System", "Planets other than earth", "Planets orbiting beyond the Asteroid belt", "Planets closer than Earth to the Sun", "An exoplanet or extrasolar planet is a planet outside our Solar System. The first evidence of an exoplanet was noted as early as 1917, but was not recognised as one."},
                    {"What's the hottest planet in the Solar System?", "Venus", "Mercury", "Earth", "Mars", "The average temperature on Venus is 462 degrees Celsius, despite Mercury being the closest to the Sun."},
                    {"What galaxy do we live in?", "Milky Way", "Andromeda", "Betelgeuse", "Triangulum", "The Milky Way is a spiral galaxy. Andromeda is the closest major galaxy to the Milky Way, and is the largest galaxy in our local group. Triangulum is the third largest galaxy, with the Milky Way in second. Betelgeuse is the second brightest star in the constellation of Orion."},
                    {"What planet has the famous Great Red Spot?", "Jupiter", "Venus", "Mars", "Neptune", "The Great Red Spot is a persistent high-pressure region in Jupiter's atmosphere, producing an anticyclonic storm. It has been continuously observed since 1830."},
                    {"Which of the following moons are the coldest?", "Europa", "Io", "Mars", "Pluto", "Europa is one of the moons of Jupiter, and is primarily made up of silicate rock. It has a water-ice crust due to its temperature constantly under -160 degrees Celsius and its poles never rising above minus 220 degrees Celsius. It's also hoped to contain extra-terrestrial life."},
                    {"What planet or moon has the most volcanic activity in the Solar System?", "Io", "Earth", "Europa", "Mercury", "Io is the most volcanically active body in our Solar Sytem due to the gravitational force exerted by Jupiter and its other moons and constantly deforming it, which generates the heat to produce its volcanism."},
                    {"How many planets are in our Solar Sytem?", "8", "9", "10", "11", "Eight. There are officially eight planets in our solar system – Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus and Neptune. Pluto is no longer classified as a planet. There are also moons, asteroids and comets as well as the Kuiper Belt which includes Pluto."},
                    {"Are the orbits of the planets on the same plane?", "Yes, more or less", "No, they're all over the place", "Yes exactly", "No such thing as a plane", "Yes, the planets orbit in or close to a plane around the Sun called the ecliptic. The ecliptic is inclined 7 degrees from the plane of the Sun’s equator."},
                    {"Which of these two are classified as Gas Giants?", "Jupiter and Saturn", "Uranus and Venus", "Jupiter and the Sun", "Neptune and Mars", "Neptune, Uranus, Jupiter and Saturn are all Gas Giants."}

            };
            for (String[] aQuizData : quizData) {
                //Prepare array
                ArrayList<String> tmpArray = new ArrayList<>();
                tmpArray.add(aQuizData[0]); //Question
                tmpArray.add(aQuizData[1]); //Answer
                tmpArray.add(aQuizData[2]); //Choice1
                tmpArray.add(aQuizData[3]); //Choice2
                tmpArray.add(aQuizData[4]); //Choice3
                tmpArray.add(aQuizData[5]); //Description

                //Add tmpArray to quizArray
                quizArray.add(tmpArray);
            }
        } else {
            String quizDataHard[][] = {
                    //{"Question", "Answer", "Choice1", "Choice2", "Choice3", Answer Description}
                    {"Approximately how long would you survive in space?", "90 Seconds", "10 seconds", "0.01 seconds", "1 minute", "While you'll be unconscious within 15 seconds, you might be able to cling on for as long as three minutes if you're somehow placed back into a pressurised space straight away after rescue, but generally you'll die after 90 seconds from asphyxiation."},
                    {"If an asteroid is 10 AU away from the Earth, how far away is it from the Earth?", "10 times the distance from the Earth to the Sun", "10 lightyears", "10 times the distance from the Earth to the edge of the Solar System", "10 times the distance from the Sun to Mercury", "The astronomical unit (or AU) is a unit of length, roughly the distance from Earth to the Sun. However, that distance varies as Earth orbits the Sun, from a maximum (aphelion) to a minimum (perihelion) and back again once a year."},
                    {"The furthest scientists have landed a probe is:", "Titan", "Mars", "Pluto", "Neptune", "Huygens was an atmospheric entry probe that landed successfully on Saturn's moon Titan in 2005. Built and operated by the European Space Agency (ESA), it was part of the Cassini–Huygens mission and became the first spacecraft ever to land on Titan and the farthest landing from Earth a spacecraft has ever made."},
                    {"Which Mars rover, launched in 2003 with an expected life-span of only 90 days, continues to be active as of 2016?", "Opportunity", "Curiosity", "Voyager 2", "Spirit", "There are 6 other rovers currently on Mars, two of which being Curiosity (launched 2011), and Spirit (launched in 2003)."},
                    {"If the star Vega has a brightness of Magnitude 0.03, and the star Arcturus has a brightness of -0.04, which star is brighter?", "Arcturus", "Vega", "Neither", "No such thing as brightness Magnitude", "The apparent magnitude (m) of a celestial object is a number that is a measure of its brightness as seen by an observer on Earth. The brighter an object appears, the lower its magnitude value (i.e. inverse relation). The Sun, at apparent magnitude of −26.7, is the brightest object in the sky."},
                    {"Around 70 percent of the universe is made up of:", "Dark Energy", "Dark Matter", "Hydrogen", "Helium", "More is unknown than is known. We know how much dark energy there is because we know how it affects the universe's expansion. Other than that, it is a complete mystery. But it is an important mystery. It turns out that roughly 68% of the universe is dark energy. Dark matter makes up about 27%. The rest - everything on Earth, everything ever observed with all of our instruments, all normal matter - adds up to less than 5% of the universe. Come to think of it, maybe it shouldn't be called 'normal' matter at all, since it is such a small fraction of the universe."},
                    {"The most distant galaxy seen as of yet is:", "GN-z11", "EGSY8p7", "Andromeda", "Q79-BTF", "The new galaxy has been named GN-z11. It takes the crown from EGSY8p7, which set the red shift record at 8.68 — the new galaxy has a red shift of 11.1. That distance means that the light left the galaxy when the universe was in its infancy. The light came just 400 million years after the universe began, 13.8 billion years ago — meaning that scientists can look back into the very formation of the universe."},
                    {"The most distant star seen in space is:", "MACS J1149+2223", "EGSY8p7", "Betelgeuse", "Alpha Centauri", "Captured by the Hubble, MACS J1149+2223 (dubbed Icarus) is the farthest individual star ever seen. It is only visible because it is being magnified by the gravity of a massive galaxy cluster, located about 5 billion light-years from Earth. "},
                    {"Which way does the Earth spin?", "From West to East", "From North to South", "From South to North", "From East to West", "That's why the Sun rises in the East and sets in the West."},
                    {"Which of these planets has no moons?", "Mercury", "Venus", "Pluto", "Saturn", "Both Mercury and Venus have no moons. Pluto, despite its size (and being a dwarf planet), has 5 moons. In order of distance from Pluto, they are Charon, Styx, Nix, Kerberos, and Hydra. Charon, the largest of the five moons, is mutually tidally locked with Pluto, and is massive enough that Pluto and Charon are sometimes considered a double dwarf planet."},
                    {"Which of these is a moon of Jupiter?", "Io", "Eucladis", "Hydra", "Nix", "It's one of the Galilean moons, along with Europa, Ganymede and Callisto. Eucladis is a moon of Saturn, while Nix and Hydra are moons of Pluto."},
                    {"Which year was the first exoplanet discovered in?", "1995", "2003", "2011", "1875", "51 Pegasi b was discovered in 1995."},
                    {"Which way were all the elements heavier than iron formed?", "In clouds", "In The Big Bang", "In the center of the Earth", "Asteroids colliding with stars", "After the Big Bang, tiny particles bound together to form hydrogen and helium. As time went on, young stars formed when clouds of gas and dust gathered under the effect of gravity, heating up as they became denser. At the stars’ cores, bathed in temperatures of over 10 million degrees C, hydrogen and then helium nuclei fused to form heavier elements. A reaction known as nucleosynthesis."},
                    {"It takes the Sun 225-250 million years to do one revolution of the Milky Way Galaxy. How fast does the Sun travel?", "220km in a second", "220km in an minute", "220 km in a hour", "220km in a year", "In space there are no particles, therefore no friction to slow an object down, and will retain the same speed until it hits something."},
                    {"How old is the Solar System?", "5 billion years", "5000 years", "5 trillion years", "500 billion years", "The solar system is presumed to have formed at the same time as the Sun, about 5 billion years ago."}
            };
            for (String[] aQuizDataHard : quizDataHard) {
                //Prepare array
                ArrayList<String> tmpArray = new ArrayList<>();
                tmpArray.add(aQuizDataHard[0]); //Question
                tmpArray.add(aQuizDataHard[1]); //Answer
                tmpArray.add(aQuizDataHard[2]); //Choice1
                tmpArray.add(aQuizDataHard[3]); //Choice2
                tmpArray.add(aQuizDataHard[4]); //Choice3
                tmpArray.add(aQuizDataHard[5]); //Description

                //Add tmpArray to quizArray
                quizArray.add(tmpArray);
            }
        }

        showNextQuiz();
    }

    @SuppressLint("SetTextI18n")
    public void showNextQuiz() {
        // Update quizCountLabel
        countLabel.setText("Question " + quizCount + " / " + totalQuestions);

        //Generate random number between 0 and 4 (quizArray's size -1)
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        //Pick one quiz set
        ArrayList<String> quiz = quizArray.get(randomNum);

        //Set question and right answer
        //Array format: {"Question", "Answer", "Choice1", "Choice2", "Choice3", "Answer Desciption"}
        questionLabel.setText(quiz.get(0));
        correctAnswer = quiz.get(1);
        answerDescription = quiz.get(5);

        //Remove "Question" from quiz and Shuffle quiz
        quiz.remove(0);
        quiz.remove(4);
        Collections.shuffle(quiz);

        //Set Choices
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));

        //Remove this quiz from quizArray
        quizArray.remove(randomNum);

    }

    public void checkAnswer(View view) {
        //Get pushed button
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if (btnText.equals(correctAnswer)) {
            //Correct
            alertTitle = "Correct!";
            correctAnswerCount++;

        } else {
            //Incorrect
            alertTitle = "Incorrect...";
        }
        //Create Dialogue.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Answer: " + correctAnswer + "\n" + answerDescription);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount == totalQuestions) {
                    //Receive quizMode from MainActivity
                    int quizMode = getIntent().getIntExtra("QUIZ_MODE", 0);
                    Log.v("MODE_OPTIONS", quizMode + "");

                    //Show result
                    Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
                    intent.putExtra("rightAnswerCount", correctAnswerCount);
                    startActivity(intent);
                } else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });

        builder.setCancelable(false);
        builder.show();

    }
}
