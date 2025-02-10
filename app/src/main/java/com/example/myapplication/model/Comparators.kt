package com.example.myapplication.model

//za sortiranje koristimo fju nekaLista.sortedWith(nekiComparator)
//uvek ce biti opadajuce liste tkd komparatori automatski sortiraju opadajuce
val winsComparator = Comparator{p1: Player, p2: Player -> p2.getNumOfWins() - p1.getNumOfWins()}
val answersComparator = Comparator{p1: Pair<Player, Player>, p2: Pair<Player, Player> -> (p2.first.getNumOfCorrectAnswers() + p2.second.getNumOfCorrectAnswers()) - (p1.first.getNumOfCorrectAnswers() + p1.second.getNumOfCorrectAnswers())}