use std::fs::File;
use std::io::prelude::*;
use std::io::BufReader;

fn main() {
    let file = File::open("../input.txt").unwrap();
    let input: Vec<i32> = BufReader::new(file)
        .lines()
        .map(|line| line.unwrap().trim().parse::<i32>().unwrap())
        .collect();

    let increase_sum = scan_window(&input);

    println!("Part 2: {}", increase_sum);
}

fn scan_window(data: &Vec<i32>) -> i32 {
    let mut prev_sum = 0;
    let mut increase_sum = 0;
    
    for i in 2..data.len() {
        let total = data[i] + data[i - 1] + data[i - 2];
        if prev_sum != 0 && total > prev_sum {
            increase_sum += 1;
        }
        prev_sum = total;
    }

    increase_sum
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test() {
        let input: Vec<i32> = "199
        200
        208
        210
        200
        207
        240
        269
        260
        263".split("\n")
        .map(|x| x.trim().parse::<i32>().unwrap())
        .collect();

        assert_eq!(scan_window(&input), 5);
    }
}
