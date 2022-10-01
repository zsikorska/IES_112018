package com.myweatherradar;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class WeatherStarter {

    private static Logger logger = LogManager.getLogger(WeatherStarter.class);

    //todo: should generalize for a city passed as argument
    private static final int CITY_ID_AVEIRO = 1010500;

    public static void  main(String[] args ) {

        int city_id;

        if (args.length == 0)
            city_id = CITY_ID_AVEIRO;
        else
            city_id = Integer.parseInt(args[0]);

        // get a retrofit instance, loaded with the GSon lib to convert JSON into objects
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create a typed interface to use the remote API (a client)
        IpmaService service = retrofit.create(IpmaService.class);
        // prepare the call to remote endpoint
        Call<IpmaCityForecast> callSync = service.getForecastForACity(city_id);

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

            if (forecast != null) {
                var firstDay = forecast.getData().listIterator().next();

                logger.info("Forecast for the " + firstDay.getForecastDate() + ":");
                logger.info( "- max temp is " + firstDay.getTMax());
                logger.info( "- min temp is " + firstDay.getTMin());
                logger.info( "- predicted wind direction is " + firstDay.getPredWindDir());

            } else {
                logger.info( "No results for this request!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
