use std::fs::File;
use std::io::prelude::*;
use std::io::BufReader;

fn main() {
    let file = File::open("../input.txt").unwrap();
    let input: Vec<i32> = BufReader::new(file)
        .lines()
        .map(|line| line.unwrap().trim().parse::<i32>().unwrap())
        .collect();

    let increase_count = scan(&input);

    println!("Part 1: {}", increase_count);
}

fn scan(data: &Vec<i32>) -> i32 {
    let mut prev_contents = 0;
    let mut increase_count = 0;
    
    for content in data {
        if prev_contents != 0 && *content > prev_contents {
            increase_count += 1;
        }
        prev_contents = *content;
    }

    increase_count
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

        assert_eq!(scan(&input), 7);
    }
}
