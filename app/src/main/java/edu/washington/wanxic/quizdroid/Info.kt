package edu.washington.wanxic.quizdroid

class Info : TopicRepository {
    private var topic : String? = null
    private var topics : Array<Topic>? = null

    val mathQuestion1 : Question = Question("1 + 1 = ?", arrayOf("1", "2", "3", "4"), 1)
    val mathQuestion2 : Question = Question("1 + 2 = ?", arrayOf("1", "2", "3", "4"), 2)
    val mathQuestion3 : Question = Question("1 + 3 = ?", arrayOf("1", "2", "3", "4"), 3)
    val mathQuestions : Collection<Question> = listOf(mathQuestion1, mathQuestion2, mathQuestion3)
    val math : Topic = Topic("Math", "Simple Math Test",
            "Take this basic math test to find out how well you know your basic math skills.", mathQuestions)

    val physicsQuestion1 : Question = Question("If an object is in motion, what kind of energy does it apply?",
            arrayOf("Potential Energy", "Caloric Energy", "Kinetic Energy", "Metabolic Energy"), 2)
    val physicsQuestion2 : Question = Question("What is the force holds back a sliding object?",
            arrayOf("Friction", "Gravity", "Momentum", "Deceleration"), 0)
    val physicsQuestion3 : Question = Question("Force is measured by what unit of measure?",
            arrayOf("Joules", "Degrees", "Newtons", "Calories"), 2)
    val physicsQuestions : Collection<Question> = listOf(physicsQuestion1, physicsQuestion2, physicsQuestion3)
    val physics : Topic = Topic("Physics", "Simple Physics Test",
            "The natural science of Physics is all around us! How much do you really " +
                    "know about this incredible branch of science?", physicsQuestions)

    val heroQuestion1 : Question = Question("Which Super Hero Team Does Johnny Storm Belong To?",
            arrayOf("Ultimate Avengers", "The Justice League", "The X-Men", "The Fantastic 4"), 3)
    val heroQuestion2 : Question = Question("What Is Anthony Stark's Super Hero Name?",
            arrayOf("Spider Man", "Iron Man", "Deadpool", "Wolverine"), 1)
    val heroQuestion3 : Question = Question("How old is Thor?",
            arrayOf("25 years old", "100 years old", "500 years old", "At lease 1000 years old"), 3)
    val heroQuestion : Collection<Question> = listOf(heroQuestion1, heroQuestion2, heroQuestion3)
    val hero : Topic = Topic("Marvel Super Heroes", "Simple Marvel Super Heros Test",
            "The Marvel Super Heroes is an American / Canadian animated television series " +
                    "starring five comic-book superheroes from Marvel Comics.", heroQuestion)


    val topicsList : ArrayList<Topic> = arrayListOf(math, physics, hero)


    override fun getTopics() : ArrayList<Topic> {
        return topicsList
    }

    override fun getTopicsName(): ArrayList<String> {
        var topicsText : ArrayList<String> = arrayListOf()
        for (item: Topic in topicsList) {
            topicsText.add(item.topic)
        }
        return topicsText
    }


    override fun getTopic(singleTopic : String) : Topic? {
        for(item : Topic in topicsList) {
            if(item.topic == singleTopic) {
                return item
            }
        }
        return null
    }

    override fun getQuestions(singleTopic : String) : Collection<Question>? {
        for(item : Topic in topicsList) {
            if(item.topic == singleTopic) {
                return item.questions
            }
        }
        return null
    }

    override fun getShortDescription(): ArrayList<String> {
        var descriptionText : ArrayList<String> = arrayListOf()
        for (item: Topic in topicsList) {
            descriptionText.add(item.shortDesciptoin)
        }
        return descriptionText //To change body of created functions use File | Settings | File Templates.
    }

}
