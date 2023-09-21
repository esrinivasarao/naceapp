package com.dbclm.nace.utility;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dbclm.nace.entity.Item;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

/**
 * @author Srinivas
 *
 */
@Component
public class CsvParser {

	List<Item> items = new ArrayList<>();

	public List<Item> parseCsvFile(String path) throws IOException {

		try (CSVReader reader = new CSVReader(new FileReader(path))) {
			CsvToBean<Item> csvToBean = new CsvToBeanBuilder<Item>(reader).withType(Item.class)
					.withIgnoreLeadingWhiteSpace(true).build();

			items = csvToBean.parse();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return items;

	}
}