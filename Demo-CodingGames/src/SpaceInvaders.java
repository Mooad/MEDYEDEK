import java.util.ArrayList;
import java.util.List;

public class SpaceInvaders {

    List<Integer> invadorsGone = new ArrayList<>();

    public SpaceInvaders(int[][] aliens, int[] position) {

        Case[][] fightArena = new Case[aliens.length][aliens[0].length];
        fightArena = fillFistMatriReceived(aliens, fightArena);

        while (!invaderArrivedAtBottom(fightArena, position) && matrixStillHaveInvaders(fightArena, position)) {
            fightArena = calculateNewState(fightArena);
            Integer invaderShouted = shootAndDeleteInvader(fightArena);

            if (invaderShouted != null) {
                invadorsGone.add(invaderShouted);
            }
        }

    }

    public List<Integer> blastSequence() {
            return (invadorsGone);

    }

    public Case[][] fillFistMatriReceived(int[][] aliens, Case[][] fightArena) {
        for (int i = 0; i < aliens.length; i++) {
            for (int j = 0; j < aliens[0].length; j++) {
                Case aCase = new Case();
                aCase.numbersInTheCase.add(aliens[i][j]);
                fightArena[i][j] = aCase;
                aCase.setCordonnates(new int[]{i,j});
            }
        }
        return fightArena;
    }


    public boolean invaderArrivedAtBottom(Case[][] fightArena ,int[] position) {
        boolean arrived = false;
        Case aCase = fightArena[position[0]][position[1]];
        if (aCase.numbersInTheCase.size() > 1)
            arrived = true;

        for (int i = fightArena[0].length - 1; i < fightArena[0].length; i++) {
            for (int j = 0; j < fightArena.length; j++) {
                if ((i != position[0] && j != position[1])) {
                    aCase = fightArena[i][j];
                    arrived = aCase.numbersInTheCase.size() > 0;
                }
            }
        }
        return arrived;
    }

    public boolean matrixStillHaveInvaders(Case[][] fightArena ,int[] position) {
        boolean invadersStillInTown = false;
        Case aCase = fightArena[position[0]][position[1]];
        if (aCase.numbersInTheCase.size() > 1)
            invadersStillInTown = true;

        for (int i = 0; i < fightArena[0].length-1; i++) {
            for (int j = 0; j < fightArena.length; j++) {
                if ((i != position[0] && j != position[1])) {
                    aCase = fightArena[i][j];
                    if(aCase.numbersInTheCase.size() > 0)
                        return true;
                }
            }
        }
        return invadersStillInTown;
    }
    public Case[][] calculateNewState(Case[][] fightArena) {
        Case[][] newFightArena = new Case[fightArena[0].length][fightArena.length];

        for (int i = 0; i < fightArena[0].length; i++) {
            for (int j = 0; j < fightArena.length; j++) {
                Case aCase = fightArena[i][j];
                for (Integer spaceInvader : aCase.getNumbersInTheCase()) {

                    if (spaceInvader != null && spaceInvader > 0) {
                        // case it's positif and it get's back to the line
                        if ((spaceInvader + aCase.cordonnates[1]) > (fightArena.length - 1)) {
                            aCase.cordonnates[0] = aCase.cordonnates[0]++;
                            aCase.cordonnates[1] = calculateNewLinePosition(true, fightArena[0].length, spaceInvader, aCase.cordonnates[1]);
                            spaceInvader = -spaceInvader;
                            newFightArena[aCase.cordonnates[0]][aCase.cordonnates[1]].getNumbersInTheCase().add(spaceInvader);
                        } else if ((spaceInvader + aCase.cordonnates[1]) < (fightArena.length - 1)) {
                            aCase.cordonnates[1] = spaceInvader + aCase.cordonnates[1];
                            newFightArena[aCase.cordonnates[0]][aCase.cordonnates[1]].getNumbersInTheCase().add(spaceInvader);
                        }
                    }
                    if (spaceInvader != null && spaceInvader < 0) {
                        // case it's positif and it get's back to the line
                        if (Math.abs((spaceInvader - aCase.cordonnates[1])) > (fightArena.length - 1)) {
                            aCase.cordonnates[0] = aCase.cordonnates[0]++;
                            aCase.cordonnates[1] = calculateNewLinePosition(false, fightArena[0].length, spaceInvader, aCase.cordonnates[1]);
                            newFightArena[aCase.cordonnates[0]][aCase.cordonnates[1]].getNumbersInTheCase().add(Math.abs(spaceInvader));
                        } else if ((spaceInvader + aCase.cordonnates[1]) < (fightArena.length - 1)) {
                            aCase.cordonnates[1] = aCase.cordonnates[1] - Math.abs(spaceInvader);
                            newFightArena[aCase.cordonnates[0]][aCase.cordonnates[1]].getNumbersInTheCase().add(Math.abs(spaceInvader));
                        }
                    }
                }
            }
        }

        return newFightArena;
    }

    public int calculateNewLinePosition(boolean positif , int tableXLength , int invader ,int invaderIndex) {
        if (positif) {
            return (--tableXLength) - (invader - ((tableXLength - (invaderIndex))));
        } else {
            return Math.abs(invader + ((invaderIndex + 1)));
        }
    }




    public Integer shootAndDeleteInvader(Case[][] fightArena) {
        Integer maxInCase = null;
        for (int i = fightArena[0].length - 2; i >= 0; i--) {
            for (int j = 0; j < fightArena.length; j++) {

                if (fightArena[i][j].numbersInTheCase.size() > 0) {
                    maxInCase = fightArena[i][j].numbersInTheCase.stream().max(Integer::compare).get();
                    Integer finalMaxInCase = maxInCase;
                    fightArena[i][j].numbersInTheCase = fightArena[i][j].numbersInTheCase.stream()
                            .filter(integer -> !integer.equals(finalMaxInCase)).toList();
                    return maxInCase;
                }
            }
        }
        return maxInCase;
    }
    }


class Case {

    public Case() {
        this.numbersInTheCase = new ArrayList<>();
    }

    List<Integer> numbersInTheCase;
    int[] cordonnates;

    public void setCordonnates(int[] cordonnates) {
        this.cordonnates = cordonnates;
    }

    public int[] getCordonnates() {
        return cordonnates;
    }

    public List<Integer> getNumbersInTheCase() {
        return numbersInTheCase;
    }

    public void setNumbersInTheCase(List<Integer> numbersInTheCase) {
        this.numbersInTheCase = numbersInTheCase;
    }


}
