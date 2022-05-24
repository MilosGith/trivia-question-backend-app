package com.milostrivia.trivia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.milostrivia.trivia.controller.TriviaQuestionController;
import org.json.simple.JSONArray;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static com.milostrivia.trivia.Util.decodeBase64;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class TriviaQuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public  void testGetQuestions() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/questions")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].questionText").exists())
                .andExpect(jsonPath("$[0].possibleAnswers").exists())
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andReturn();
    }

    @Test
    public void testCheckAnswer() {
        TriviaQuestionController controller = new TriviaQuestionController();
        TriviaQuestionStorage.getInstance().clearStorage();
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Yes", "No"));
        TriviaQuestion question = new TriviaQuestion(TriviaQuestionStorage.getInstance().getNewID(),"Is this question written in english", "Yes", list);
        TriviaQuestionStorage.getInstance().addQuestion(question, "Yes");
        TriviaQuestion question1 = TriviaQuestionStorage.getInstance().getQuestionByID(0);
        System.out.println(question1.getQuestionText());
        System.out.println(TriviaQuestionStorage.getInstance().getAnswerForQuestion(0));
        Answer correctAnswer = new Answer(0, "Yes");
        Answer wrongAnswer = new Answer(0, "No");

        Response wrongResponse = controller.checkanswer(wrongAnswer);
        assert(!wrongResponse.getIs_correct());

        Response correctResponse = controller.checkanswer(correctAnswer);
        assert(correctResponse.getIs_correct());
    }
}




//    MvcResult resultWrongAnswer = mockMvc.perform(MockMvcRequestBuilders.post("/checkanswer")
//                    .content(asJsonString(wrongAnswer))
//                    .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(0))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.response").value("The answer is not correct"))
//            .andReturn();
//
//    String result =  resultWrongAnswer.getResponse().getContentAsString();
//        System.out.println(result);
//
//                MvcResult resultCorrectAnswer = mockMvc.perform(MockMvcRequestBuilders.post("/checkanswer")
//                .content(asJsonString(correctAnswer))
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(0))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.response").value("The answer is correct"))
//                .andReturn();