use std::fs::File;
use std::io::BufReader;
use std::io::prelude::*;

fn main() {
    let file = File::open("input.txt").unwrap();
    let input: Vec<String> = BufReader::new(file)
        .lines()
        .collect::<Result<_, _>>().unwrap();

    let (gamma, epsilon) = parse(&input);

    println!("Part 1: {:?} == 2640986", product(&gamma, &epsilon));
    
    let o2 = filter(&input, gamma.chars().nth(0).unwrap(), '1', '0', '1');
    let co2 = filter(&input, epsilon.chars().nth(0).unwrap(), '0', '1', '0');

    println!("Part 2: {:?} == 6822109", product(&o2, &co2));
}

fn product(first: &String, second: &String) -> u32 {
    u32::from_str_radix(&first, 2).unwrap() * u32::from_str_radix(&second, 2).unwrap()
}

fn parse(data: &Vec<String>) -> (String, String) {
    let length = data.get(0).unwrap().len();
    let common_point :i32 = (data.len() / 2).try_into().unwrap();

    let result = data.iter().fold(vec![0; length], |mut counters, line| {
        for (i, _) in line.chars().enumerate().filter(|(_, x)| *x == '1') {
            counters[i] += 1;
        }
        counters
    });

    let mut gamma: String = String::new();
    let mut epsilon: String = String::new();
    for count in result {
        if count > common_point {
            gamma.push('1');
            epsilon.push('0');
        } else {
            gamma.push('0');
            epsilon.push('1');
        }
    }

    (gamma, epsilon)
}

fn filter(data: &Vec<String>, first :char, greater :char, lower :char, same :char) -> String {
    let mut filtered = data.clone();
    let mut pos = 0;
    let mut bit = first;

    loop {
        filtered.retain(|line| line.chars().nth(pos) == Some(bit));
        if filtered.len() == 1 {
            break;
        }

        pos += 1;
        let sum = filtered.iter().fold(0, |acc, line| acc + line.chars().nth(pos).unwrap().to_digit(2).unwrap());
        let size = filtered.len() as u32;
        if sum > (size - sum) {
            bit = greater;
        } else if sum == (size - sum) {
            bit = same;
        } else {
            bit = lower;
        }
    }

    filtered.get(0).unwrap().to_string()
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

        let (gamma, epsilon) = parse(&input);
        assert_eq!(product(&gamma, &epsilon), 198);
    
        let o2 = filter(&input, gamma.chars().nth(0).unwrap(), '1', '0', '1');
        let co2 = filter(&input, epsilon.chars().nth(0).unwrap(), '0', '1', '0');
    
        assert_eq!(product(&o2, &co2), 230);
    }
}