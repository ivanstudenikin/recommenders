import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.svd.Factorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDPlusPlusFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;

public class SampleRecommender {

    public static void main(String[] args) throws IOException, TasteException {
        final long startTime = System.currentTimeMillis();
        List<RecommendedItem> recommendedItems = recommendSVD(111, 10);
        for (RecommendedItem recommendation : recommendedItems) {
            System.out.println("userID: " + 111);
            System.out.println(recommendation);
        }
        final long elapsedTimeMillis = System.currentTimeMillis() - startTime;
        System.out.println("took " + elapsedTimeMillis + " ms");

    }

    public static List<RecommendedItem> recommendSVD(long userId, int numberOfItemsToRecommend) throws IOException,
            TasteException {
        DataModel model = new FileDataModel(new File("/Users/ivan.studenikin/data/baka/ratings.csv"));

        int numberOfFeatures = 40;
        int numberOfIterations = 100;

        Factorizer factorizer = new SVDPlusPlusFactorizer(model, numberOfFeatures, numberOfIterations);
        Recommender recommender = new SVDRecommender(model, factorizer);
        return recommender.recommend(userId, numberOfItemsToRecommend);
    }

}
