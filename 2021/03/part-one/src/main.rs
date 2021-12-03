use std::fs::File;
use std::io::BufReader;
use std::io::prelude::*;

fn main() {
    let file = File::open("../input.txt").unwrap();
    let input: Vec<String> = BufReader::new(file)
        .lines()
        .collect::<Result<_, _>>().unwrap();

    let result = power(&input, 12);

    println!("Part 1: {}", result);
}

fn power(data: &Vec<String>, size: i32) -> i32 {
    let mut counters: Vec<i32> = vec![0; size.try_into().unwrap()];
    let mut records = 0;

    for line in data {
        let chars: Vec<char> = line.chars().collect();

        let mut count = 0;
        for c in chars {
            counters[count] += c.to_digit(10).unwrap() as i32;
            count += 1;
        }
        records += 1;
    }

    let mut gamma: String = String::new();
    let mut epsilon: String = String::new();
    for count in counters {
        if count > records / 2 {
            gamma += "1";
            epsilon += "0";
        } else {
            gamma += "0";
            epsilon += "1";
        }
    }

    i32::from_str_radix(&gamma, 2).unwrap() * i32::from_str_radix(&epsilon, 2).unwrap()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test() {
        let input: Vec<String> = "00100
        11110
        10110
        10111
        10101
        01111
        00111
        11100
        10000
        11001
        00010
        01010".split("\n")
        .map(|s| s.trim().to_string())
        .collect();

        assert_eq!(power(&input, 5), 198);
    }
}