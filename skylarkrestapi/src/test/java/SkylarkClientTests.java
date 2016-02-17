import com.clockworkant.skylark.api.SkylarkClient;
import com.clockworkant.skylark.api.model.Episode;
import com.clockworkant.skylark.api.model.Item;
import com.clockworkant.skylark.api.model.SkylarkSet;

import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SkylarkClientTests {

    private final SkylarkClient skylarkClient;

    public SkylarkClientTests() {
        skylarkClient = new SkylarkClient();
    }

    @Test
    public void testFetchSet() throws Exception {
        skylarkClient.fetchSets().subscribe(new Action1<List<SkylarkSet>>() {
            @Override
            public void call(List<SkylarkSet> skylarkSets) {
                for (SkylarkSet set : skylarkSets) {
                    System.out.println(set.toString());
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                System.err.println(throwable.getMessage());
            }
        });


    }

    @Test
    public void testPrintAllItems() throws Exception {
        skylarkClient.fetchSets().subscribe(new Action1<List<SkylarkSet>>() {
            @Override
            public void call(List<SkylarkSet> skylarkSets) {
                for (SkylarkSet set : skylarkSets) {
                    for (Item item : set.getItems()) {
                        System.out.println(item.getContent_type());
                        System.out.println(item.getContent_url());
                    }
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                System.err.println(throwable.getMessage());
            }
        });
    }

    @Test
    public void testFetchEpisode() throws Exception {
        Item item = mock(Item.class);
        when(item.getContent_url()).thenReturn("/api/episodes/film_7a99b420e91e4513ac56cf12f3fb82f8/");
        skylarkClient.fetchEpisode(item).subscribe(new Action1<Episode>() {
            @Override
            public void call(Episode episode) {
                System.out.println(episode.toString());
            }
        });
    }

    @Test
    public void testPrintAllEpisodeImages() throws Exception {
        skylarkClient.fetchSets()
                .flatMapIterable(new Func1<List<SkylarkSet>, Iterable<SkylarkSet>>() {
                    @Override
                    public Iterable<SkylarkSet> call(List<SkylarkSet> skylarkSets) {
                        return skylarkSets;
                    }
                })
                .flatMapIterable(new Func1<SkylarkSet, Iterable<Item>>() {

                    @Override
                    public Iterable<Item> call(SkylarkSet skylarkSet) {
                        return skylarkSet.getItems();
                    }
                })
                .flatMap(new Func1<Item, Observable<Episode>>() {
                    @Override
                    public Observable<Episode> call(Item item) {
                        return skylarkClient.fetchEpisode(item);
                    }
                }).subscribe(new Action1<Episode>() {
                    @Override
                    public void call(Episode episode) {
                        for (String image : episode.getImage_urls()) {
                            System.out.println(image);
                        }
                    }
        });

    }

    @Test
    public void testPrintAnySetsWithTempImage() throws Exception {
        skylarkClient.fetchSets().flatMapIterable(new Func1<List<SkylarkSet>, Iterable<SkylarkSet>>() {
            @Override
            public Iterable<SkylarkSet> call(List<SkylarkSet> skylarkSets) {
                return skylarkSets;
            }
        }).filter(new Func1<SkylarkSet, Boolean>() {
            @Override
            public Boolean call(SkylarkSet skylarkSet) {
                String tempImage = skylarkSet.getTempImage();
                return tempImage != null && tempImage.trim().length() != 0;
            }
        }).subscribe(new Action1<SkylarkSet>() {
            @Override
            public void call(SkylarkSet skylarkSet) {
                System.out.println(skylarkSet.getTitle() + " " + skylarkSet.getTempImage());
            }
        });

    }
}
