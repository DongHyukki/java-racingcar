package step4;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultViewTest {

    private ResultView resultView;
    private List<Car> resultCarPositions;
    private List<List<Car>> moveCarPositions = new ArrayList<>();

    @BeforeEach
    void setUp() {
        resultCarPositions = Stream.of(new Car("pobi", "-----"), new Car("crong", "----"),
                                       new Car("honux", "-----"))
                                   .collect(
                                           Collectors.toList());

        moveCarPositions.add(Stream.of(new Car("pobi", "-"), new Car("crong", "-"),
                                       new Car("honux", "-"))
                                   .collect(
                                           Collectors.toList()));
        moveCarPositions.add(Stream.of(new Car("pobi", "--"), new Car("crong", "-"),
                                       new Car("honux", "--"))
                                   .collect(
                                           Collectors.toList()));

        moveCarPositions.add(Stream.of(new Car("pobi", "---"), new Car("crong", "--"),
                                       new Car("honux", "---"))
                                   .collect(
                                           Collectors.toList()));

        moveCarPositions.add(Stream.of(new Car("pobi", "----"), new Car("crong", "---"),
                                       new Car("honux", "----"))
                                   .collect(
                                           Collectors.toList()));

        moveCarPositions.add(resultCarPositions);
        resultView = new ResultView("pobi,honux", resultCarPositions, moveCarPositions);
    }

    @DisplayName("자동차 경주 게임에서 누가 승리했는지를 알려준다.")
    @Test
    void resultWinnerUser() {
        assertThat(resultView.resultWinnerUser()).isEqualTo("pobi,honux가 최종 우승했습니다.");
    }

    @DisplayName("자동차 경주 게임의 움직임 과정을 출력한다.")
    @Test
    void moveCarPositions() {
        assertThat(resultView.printMoveProcess()).isEqualTo("pobi:-\n"
                                                            + "crong:-\n"
                                                            + "honux:-\n"
                                                            + "\n"
                                                            + "pobi:--\n"
                                                            + "crong:-\n"
                                                            + "honux:--\n"
                                                            + "\n"
                                                            + "pobi:---\n"
                                                            + "crong:--\n"
                                                            + "honux:---\n"
                                                            + "\n"
                                                            + "pobi:----\n"
                                                            + "crong:---\n"
                                                            + "honux:----\n"
                                                            + "\n"
                                                            + "pobi:-----\n"
                                                            + "crong:----\n"
                                                            + "honux:-----\n"
                                                            + "\n");
    }

    @DisplayName("자동차 경주 게임에서 마지막 결과를 자동차의 이름과 결과 개행해서 보여준다.")
    @Test
    void resultCarPositions() {
        assertThat(resultView.printResult()).isEqualTo("pobi:-----\n"
                                                       + "crong:----\n"
                                                       + "honux:-----\n");
    }

}
