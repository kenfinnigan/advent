use std::fs::File;
use std::io::BufReader;
use std::io::prelude::*;

fn main() {
    let file = File::open("../input.txt").unwrap();
    let input: Vec<String> = BufReader::new(file)
        .lines()
        .collect::<Result<_, _>>().unwrap();

    let result = position_aim(&input);

    println!("Part 2: {}", result);
}

fn position_aim(data: &Vec<String>) -> i32 {
    let mut forward = 0;
    let mut aim = 0;
    let mut depth = 0;

    for line in data {
        let mut toks = line.split(" ").fuse();
        let command = toks.next();
        let amount: i32 = toks.next().unwrap().trim().parse().unwrap();

        match command {
            Some("forward") => {
                forward += amount;
                depth += aim * amount;
            },
            Some("down") => aim += amount,
            Some("up") => aim -= amount,
            _ => println!("invalid input {:?}", line),
        }
    }

    forward * depth
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test() {
        let input: Vec<String> = "forward 5\ndown 5\nforward 8\nup 3\ndown 8\nforward 2".split("\n")
        .map(|s| s.to_string())
        .collect();

        assert_eq!(position_aim(&input), 900);
    }
}