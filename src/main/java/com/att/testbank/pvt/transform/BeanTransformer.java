package com.att.testbank.pvt.transform;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanTransformer {

	@Autowired
	private DozerBeanMapper beanMapper;

	public <T, K> T map(K source, Class<T> destinationClass) {
		return beanMapper.map(source, destinationClass);
	}

	public <T, K> List<T> map(List<K> sourceCollection, Class<T> destinationClass) {
		List<T> destinationCollection = new ArrayList<T>();
		if (CollectionUtils.isNotEmpty(sourceCollection)) {
			for (K collectionEntity : sourceCollection) {
				destinationCollection.add(beanMapper.map(collectionEntity, destinationClass));
			}
		}
		return destinationCollection;
	}

}