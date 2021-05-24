cmake_minimum_required(VERSION 3.1)

project(currency-service)

set(CMAKE_CXX_STANDARD 11)

add_library(currency-service-lib
        src/controller/StructTmExtension.hpp
        src/AppComponents.hpp
        src/App.cpp
        src/controller/CurrencyService.hpp
        src/controller/CurrencyService.cpp
        src/dto/CurrencyData.hpp
        src/client/CurrencyClient.hpp
)

find_package(oatpp 1.2.5 REQUIRED)
find_package(oatpp-curl 1.2.5 REQUIRED)

target_link_libraries(currency-service-lib
        PUBLIC oatpp::oatpp
        PUBLIC oatpp::oatpp-curl
)

target_include_directories(currency-service-lib PUBLIC src)

add_executable(currency-service-exe
        src/App.cpp)
target_link_libraries(currency-service-exe currency-service-lib)
add_dependencies(currency-service-exe currency-service-lib)


target_link_libraries(currency-service-lib)

set_target_properties(currency-service-lib currency-service-exe PROPERTIES
        CXX_STANDARD 11
        CXX_EXTENSIONS OFF
        CXX_STANDARD_REQUIRED ON
)
