# Optimal Inventory Control System for Harvey's Specialty Shop

## Introduction
This project was developed collaboratively by students from the Computer Engineering and Industrial Engineering departments as a term project. The goal of this software is to determine the optimal lot size (𝑄) and reorder point (𝑅) for inventory management, using given cost parameters and demand data.

## Problem Statement
The software is designed to address the following problem: 

Develop a software that determines the optimal lot size (𝑄) and reorder point (𝑅) for a given data set, specifically for Harvey's Specialty Shop, which sells a popular mustard. The key features and functionalities of the software are as follows:

1. **Cost Inputs and Holding Cost Calculation:**
   - Request user inputs for unit cost, ordering cost, penalty cost, and interest rate.
   - Calculate holding cost based on the unit cost and the provided interest rate.

2. **Demand Inputs and Annual Demand Calculation:**
   - Request user inputs for lead time, lead time demand, and lead time standard deviation.
   - Calculate the annual demand based on the lead time and lead time demand.

3. **Integration of Z-Chart and Loss Function Table:**
   - Automatically lookup normal distribution values and loss function values from the Z-Chart and Loss Function Table during iteration.

4. **Determination of Decision Variables and Performance Measures:**
   - Calculate and display the following:
     - Optimal lot size (𝑄)
     - Reorder point (𝑅)
     - Number of iterations to obtain optimal values
     - Safety stock
     - Average annual holding, setup, and penalty costs
     - Average time between order placements
     - Proportion of order cycles without stockouts
     - Proportion of demands not met

5. **User Interface:**
   - Present all performance measures on a single screen for user convenience.

## Technologies Used
The software was developed using the following technologies:

- **Java Programming Language:** The core logic and calculations of the software are implemented in Java, which is known for its portability and performance.
- **Apache Commons Math Library:** The `NormalDistribution` class from the Apache Commons Math library is used to perform statistical calculations, including cumulative probabilities and density functions.

## Test Problem
To verify the software's accuracy and reliability, it has been tested with the following scenario:

**Scenario:**
Harvey’s Specialty Shop sells a popular mustard with the following parameters:
- Unit cost: $20 per jar
- Annual interest rate: 25%
- Loss of goodwill cost: $20 per jar (in case of stockout)
- Ordering cost: $100 per order
- Lead time: 4 months
- Average demand during lead time: 500 jars
- Standard deviation of demand during lead time: 100 jars

Assuming the demand follows a normal distribution, the software determines the optimal inventory control parameters for Harvey.

## Usage Instructions
1. **Input Parameters:**
   - Open the software and enter the required cost parameters (unit cost, ordering cost, penalty cost, interest rate).
   - Enter the lead time, lead time demand, and lead time standard deviation.

2. **Calculation and Results:**
   - The software will calculate the holding cost and annual demand based on the provided inputs.
   - Using the integrated Z-Chart and Loss Function Table, the software will iterate to find the optimal lot size and reorder point.
   - The results, including all performance measures, will be displayed on the screen.

## Conclusion
This software provides an efficient solution for determining the optimal lot size and reorder point, helping businesses like Harvey's Specialty Shop to manage their inventory effectively, minimize costs, and meet customer demand reliably.

## Contributors
This project was developed by the collaborative efforts of:
- Computer Engineering Students
    - Emrecan Dönmez
    - Barış Atasoy
    - Sevde Seçer
    - Çağatay Yeşilyurt
- Industrial Engineering Students
    - Seyyid Ali Sağır
    - Melih Kurt
    - Ecem Armay

We hope this software assists in enhancing your inventory management practices.
